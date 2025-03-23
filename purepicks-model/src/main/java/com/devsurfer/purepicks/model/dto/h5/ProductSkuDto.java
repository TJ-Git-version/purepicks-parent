package com.devsurfer.purepicks.model.dto.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "H5端-商品sku查询条件实体")
public class ProductSkuDto {

    @Schema(description = "关键字")
    private String keyword;

    @Schema(description = "品牌查询")
    private Long brandId;

    @Schema(description = "一级分类id")
    private Long category1Id;

    @Schema(description = "二级分类id")
    private Long category2Id;

    @Schema(description = "三级分类id")
    private Long category3Id;

    @Schema(description = "排序: 销量 1 | 价格升序 2 | 价格降序 3")
    private Integer order;

}
