package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.devsurfer.purepicks.manager.mapper.FileMapper;
import com.devsurfer.purepicks.manager.service.FileService;
import com.devsurfer.purepicks.model.dto.system.file.FileInfoQueryDto;
import com.devsurfer.purepicks.model.entity.file.FileInfo;
import com.devsurfer.purepicks.model.enums.file.FileTypeEnums;
import com.devsurfer.purepicks.model.properties.MinioProperties;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.file.FileInfoVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import com.devsurfer.purepicks.utils.FileHashUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.minio.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/14 23:26
 * description TODO
 */
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private final MinioClient minioClient;

    private final MinioProperties minioProperties;

    private final FileMapper fileMapper;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // 文件为空抛出异常
            if (file == null || file.isEmpty()) {
                PurePicksException.error(ResultCodeEnum.FILE_IS_EMPTY_ERROR);
            }
            // 获取文件输入流
            InputStream is = file.getInputStream();
            // 获取文件大小
            long fileSize = file.getSize();
            // 获取文件名称,包括后缀类型
            String originalFilename = file.getOriginalFilename();
            // 获取文件名
            String fileName = "";
            // 生成UUID
            String uuid = UUID.randomUUID().toString().replace("-", "");
            // 获取文件后缀名
            String fileExtension = "";
            if (StrUtil.isNotBlank(originalFilename)) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                fileName = fileName.replace(fileExtension, "");
            } else {
                fileName = uuid.substring(0, 6);
                fileExtension = ".txt";
            }
            FileTypeEnums fileTypeEnums = FileTypeEnums.getFileTypeByExtension(fileExtension);
            if (fileTypeEnums == null) {
                PurePicksException.error(ResultCodeEnum.FILE_TYPE_NOT_ENTITY_ERROR);
            }
            // 生成最终文件名
            String uploadFileName = StrUtil.format("{}{}{}", uuid.substring(0, 10), System.currentTimeMillis(), fileExtension);

            // 生成存储：路径+文件名
            LocalDate now = LocalDate.now();
            String pathFileName = StrUtil.format("/{}/{}/{}/{}", now.getYear(), now.getMonthValue(), now.getDayOfMonth(), uploadFileName);

            // 判断存储桶是否操作，不操作则新建
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucket()).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucket()).build());
                logger.info("创建【{}】存储桶成功！", minioProperties.getBucket());
            }

            // 上传文件
            minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucket()).stream(is, fileSize, -1).object(pathFileName).build());
            String url = StrUtil.format("http:/{}:{}/{}{}", minioProperties.getEndpoint(), minioProperties.getPort(), minioProperties.getBucket(), pathFileName);
            logger.info("文件上传成功：{}", url);

            // 保存文件信息
            insertFileInfo(originalFilename, uploadFileName, fileSize, fileTypeEnums, fileExtension, url, pathFileName, is);
            return url;
        } catch (Exception e) {
            logger.info("file upload error", e);
            PurePicksException.error(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
        return "";
    }

    private void insertFileInfo(String originalFilename, String uploadFileName, long fileSize, FileTypeEnums fileTypeEnums, String fileExtension, String url, String pathFileName, InputStream is) throws NoSuchAlgorithmException, IOException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setOriginalFilename(originalFilename);
        fileInfo.setFinalFilename(uploadFileName);
        fileInfo.setFileSize(fileSize);
        fileInfo.setFileType(fileTypeEnums.getType());
        fileInfo.setFileExtension(fileExtension);
        fileInfo.setUploadUid(AuthContextUtil.getUserId());
        fileInfo.setFileUrl(url);
        fileInfo.setFilePath(pathFileName);
        fileInfo.setChecksum(FileHashUtil.getFileHash(is, "SHA-256"));
        fileMapper.insertFile(fileInfo);
    }

    @Override
    public void download(Long fileId, HttpServletResponse response) {
        FileInfo fileInfo = fileMapper.selectByFileId(fileId);
        if (fileInfo == null) {
            PurePicksException.error(ResultCodeEnum.FILE_NOT_ENTITY_ERROR);
        }
        if (fileInfo.getFileStatus() == 2) {
            PurePicksException.error(ResultCodeEnum.FILE_NOT_USER_ERROR);
        }
        setResponseSetting(response, fileInfo.getFilePath());
        try (
                InputStream is = minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(minioProperties.getBucket())
                                .object(fileInfo.getFilePath())
                                .build());
                OutputStream os = response.getOutputStream();
        ) {
            IOUtils.copy(is, response.getOutputStream());
            os.flush();
        } catch (Exception e) {
            logger.info("download error: " + e);
            PurePicksException.error(ResultCodeEnum.FILE_DOWNLOAD_ERROR);
        }
    }

    @Override
    public PageInfo<FileInfoVo> pageFileList(FileInfoQueryDto fileInfoQueryDto) {
        try (
                Page<FileInfoVo> page = PageHelper.startPage(fileInfoQueryDto.getPageNum(), fileInfoQueryDto.getPageSize());
        ) {
            fileMapper.findFileList(fileInfoQueryDto);
            return page.toPageInfo();
        }
    }

    @Override
    public void disableFile(Long fileId) {
        fileMapper.updateFileStatus(2, fileId);
    }

    @Override
    public void enableFile(Long fileId) {
        fileMapper.updateFileStatus(1, fileId);
    }

    private static void setResponseSetting(HttpServletResponse response, String filename) {
        response.setContentType("application/octet-stream"); // 通常使用 application/octet-stream 表示二进制文件
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\""); // 强制下载文件并设置文件名
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 禁用缓存
        response.setHeader("Pragma", "no-cache"); // 禁用缓存
        response.setDateHeader("Expires", 0); // 禁用缓存
    }
}
