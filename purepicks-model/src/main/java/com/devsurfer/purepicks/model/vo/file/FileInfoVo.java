package com.devsurfer.purepicks.model.vo.file;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/15 23:20
 * description 文件信息响应实体
 */
@Data
@Tag(name = "文件信息响应实体")
public class FileInfoVo {

    @Schema(description = "文件唯一标识")
    private Long fileId;

    @Schema(description = "原始文件名")
    private String originalFilename;

    @Schema(description = "存储名称")
    private String finalFilename;

    @Schema(description = "文件大小（字节）")
    private Long fileSize;

    @Schema(description = "文件类型")
    private Integer fileType;

    @Schema(description = "文件扩展名")
    private String fileExtension;

    @Schema(description = "上传时间")
    private Date uploadTime;

    @Schema(description = "上传者（用户id）")
    private Long uploadUid;

    @Schema(description = "文件访问url")
    private String fileUrl;

    @Schema(description = "文件存储路径")
    private String filePath;

    @Schema(description = "文件状态:1:active 或 2:deleted")
    private String fileStatus;

    @Schema(description = "文件哈希值（如 sha256）")
    private String checksum;

}
