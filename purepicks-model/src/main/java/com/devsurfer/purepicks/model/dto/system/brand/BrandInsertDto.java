package com.devsurfer.purepicks.model.dto.system.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
@Tag(name = "品牌信息新增实体", description = "品牌信息新增实体")
public class BrandInsertDto {

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "品牌LOGO")
    private String logo;

    @Schema(description = "品牌排序")
    private Integer sort;

}
