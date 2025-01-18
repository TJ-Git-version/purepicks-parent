package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.FileService;
import com.devsurfer.purepicks.model.dto.system.file.FileInfoQueryDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.system.file.FileInfoVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/14 23:17
 * description TODO
 */
@Tag(name = "文件管理")
@RestController
@RequestMapping("/admin/file")
@AllArgsConstructor
public class FileController {

    private final FileService fileService;

    @Operation(summary = "上传文件", description = "上传文件")
    @PostMapping("upload")
    public ResultUtil<String> upload(@RequestParam("file") MultipartFile file) {
        return ResultUtil.ok(fileService.uploadFile(file));
    }

    @Operation(summary = "下载文件", description = "下载文件")
    @GetMapping("download/{fileId}")
    public void download(@PathVariable("fileId") Long fileId, HttpServletResponse response) {
        fileService.download(fileId, response);
    }

    @Operation(summary = "分页查询文件列表", description = "分页查询文件列表")
    @GetMapping("page-file-list")
    public ResultUtil<PageInfo<FileInfoVo>> pageFileList(FileInfoQueryDto fileInfoQueryDto) {
        return ResultUtil.ok(fileService.pageFileList(fileInfoQueryDto));
    }

    @Operation(summary = "禁用文件", description = "禁用文件")
    @PutMapping("disable-file/{fileId}")
    public ResultUtil<?> disableFile(@PathVariable("fileId") Long fileId) {
        fileService.disableFile(fileId);
        return ResultUtil.ok();
    }
    @Operation(summary = "启用文件", description = "启用文件")
    @PutMapping("enable-file/{fileId}")
    public ResultUtil<?> enableFile(@PathVariable("fileId") Long fileId) {
        fileService.enableFile(fileId);
        return ResultUtil.ok();
    }

}
