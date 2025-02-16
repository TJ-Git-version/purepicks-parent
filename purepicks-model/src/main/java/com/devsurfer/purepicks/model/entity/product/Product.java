package com.devsurfer.purepicks.model.entity.product;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 16:03
 * description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Tag(name = "商品实体", description = "商品实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "品牌id")
    private Long brandId;

    @Schema(description = "轮播图")
    private String carouselUrl;

    @Schema(description = "一级分类")
    private Long categoryId1;

    @Schema(description = "二级分类")
    private Long categoryId2;

    @Schema(description = "三级分类")
    private Long categoryId3;

    @Schema(description = "计量单位")
    private String unitName;

    @Schema(description = "商品规格id")
    private Long productSpecId;

    @Schema(description = "商品规格信息")
    private String productSpecInfo;

    @Schema(description = "商品状态：1-上架，2-下架")
    private Integer status;

    @Schema(description = "创建人id")
    private Long createUserId;

    @Schema(description = "审核人id")
    private Long reviewUserId;

    @Schema(description = "审核状态：0-未审核，1-通过，2-驳回")
    private Integer reviewStatus;

    @Schema(description = "审核信息")
    private String reviewInfo;

}
