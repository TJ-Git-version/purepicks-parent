package com.devsurfer.purepicks.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Tag(name = "商品规格更新实体", description = "商品规格更新实体")
@Data
public class ProductSpecUpdateDto {

    @Schema(description = "商品规格ID")
    private Long id;

    @Schema(description = "商品规格名称")
    private String specName;

    @Schema(description = "商品规格值")
    private String specValue;

}
