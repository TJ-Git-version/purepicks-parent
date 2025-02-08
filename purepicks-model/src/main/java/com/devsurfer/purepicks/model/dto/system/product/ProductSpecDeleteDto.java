package com.devsurfer.purepicks.model.dto.system.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Tag(name = "商品规格删除实体", description = "商品规格删除实体")
@Data
public class ProductSpecDeleteDto {

    @Schema(description = "商品规格ID")
    private Long productSpecId;

}
