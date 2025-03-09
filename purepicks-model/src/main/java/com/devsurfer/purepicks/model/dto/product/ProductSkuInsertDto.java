package com.devsurfer.purepicks.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:59
 * description TODO
 */
@Tag(name = "商品sku新增实体", description = "商品sku新增实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuInsertDto {

    @Schema(description = "商品id")
    private Long productId;

    @Schema(description = "商品SKU编码")
    private String code;

    @Schema(description = "商品SKU名称")
    private String name;

    @Schema(description = "商品SKU图片")
    private String thumbImage;

    @Schema(description = "售价")
    private BigDecimal salePrice;

    @Schema(description = "市场价")
    private BigDecimal marketPrice;

    @Schema(description = "成本价")
    private BigDecimal costPrice;

    @Schema(description = "规格名称")
    private String specName;

    @Schema(description = "重量")
    private double weight;

    @Schema(description = "体积")
    private double volume;

    @Schema(description = "库存量")
    private Integer stockNum;

}
