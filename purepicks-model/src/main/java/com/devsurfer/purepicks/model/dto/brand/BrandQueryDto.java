package com.devsurfer.purepicks.model.dto.brand;

import com.devsurfer.purepicks.model.dto.base.BaseQueryPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name = "品牌信息查询实体", description = "品牌信息查询实体")
public class BrandQueryDto extends BaseQueryPageDto {

    @Schema(description = "品牌名称")
    private String keyword;

}
