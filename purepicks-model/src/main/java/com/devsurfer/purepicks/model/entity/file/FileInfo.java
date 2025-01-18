package com.devsurfer.purepicks.model.entity.file;

import lombok.Data;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/15 23:15
 * description 文件管理
 */
@Data
public class FileInfo {

    // 文件唯一标识
    private Long fileId;

    // 原始文件名
    private String originalFilename;

    // 存储名称
    private String finalFilename;

    // 文件大小（字节）
    private Long fileSize;

    // 文件类型
    private Integer fileType;

    // 文件扩展名
    private String fileExtension;

    // 上传时间
    private Date uploadTime;

    // 上传者（用户id）
    private Long uploadUid;

    // 文件访问url
    private String fileUrl;

    // 文件存储路径
    private String filePath;

    // 文件状态:1:active 或 2:deleted
    private Integer fileStatus;

    // 文件哈希值（如 sha256）
    private String checksum;

}
