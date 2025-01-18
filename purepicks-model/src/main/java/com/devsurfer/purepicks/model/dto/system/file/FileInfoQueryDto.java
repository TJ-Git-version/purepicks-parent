package com.devsurfer.purepicks.model.dto.system.file;

import com.devsurfer.purepicks.model.dto.base.BaseQueryPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 14:19
 * description 文件请求实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name = "文件请求实体")
public class FileInfoQueryDto extends BaseQueryPageDto {

    @Schema(description = "查询条件: 文件名,文件扩展名")
    private String keyword;

    @Schema(description = "文件类型")
    private List<Integer> fileTypeList;

    @Schema(description = "上传者(用户id)")
    private List<Long> uploadUidList;

    @Schema(description = "上传时间:开始时间")
    private String uploadStartTime;

    @Schema(description = "上传时间:结束时间")
    private String uploadEndTime;

    @Schema(description = "文件状态: 1 active | 2 deleted ")
    private List<Integer> fileStatusList;

}
