package com.devsurfer.purepicks.model.dto.system.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 14:37
 * description 分类品牌关联实体
 */
@Data
@Tag(name = "分类品牌关联实体", description = "分类品牌关联实体")
public class CategoryBrandDto {

    @Schema(description = "品牌ID")
    private Long brandId;

    @Schema(description = "关联分类ID列表")
    private List<Long> categoryIdList;

}
