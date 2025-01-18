package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.file.FileInfoQueryDto;
import com.devsurfer.purepicks.model.vo.system.file.FileInfoVo;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/14 23:26
 * description TODO
 */
public interface FileService {

    String uploadFile(MultipartFile file);

    void download(Long fileId, HttpServletResponse response);

    PageInfo<FileInfoVo> pageFileList(FileInfoQueryDto fileInfoQueryDto);

    void disableFile(Long fileId);

    void enableFile(Long fileId);

}
