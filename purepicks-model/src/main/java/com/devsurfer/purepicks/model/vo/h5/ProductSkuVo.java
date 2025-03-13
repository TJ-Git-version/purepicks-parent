package com.devsurfer.purepicks.model.vo.h5;

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
@Tag(name = "H5-商品sku响应实体", description = "商品sku响应实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSkuVo {

    @Schema(description = "商品skuId")
    private Long id;

    @Schema(description = "商品id")
    private Long productId;

    @Schema(description = "商品SKU编码")
    private String skuCode;

    @Schema(description = "商品SKU名称")
    private String skuName;

    @Schema(description = "商品SKU图片")
    private String thumbImage;

    @Schema(description = "销售数量")
    private Long saleNum;

    @Schema(description = "售价")
    private BigDecimal salePrice;

    @Schema(description = "市场价")
    private BigDecimal marketPrice;

    @Schema(description = "成本价")
    private BigDecimal costPrice;

    @Schema(description = "规格名称")
    private String skuSpec;

    @Schema(description = "重量")
    private double weight;

    @Schema(description = "体积")
    private double volume;

    @Schema(description = "库存量")
    private Integer stockNum;

    @Schema(description = "状态: 0-下架, 1-上架")
    private Integer status;

}
