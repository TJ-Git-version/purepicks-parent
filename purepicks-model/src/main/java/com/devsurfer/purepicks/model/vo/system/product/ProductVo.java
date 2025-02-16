package com.devsurfer.purepicks.model.vo.system.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 16:03
 * description TODO
 */
@Tag(name = "商品响应实体", description = "商品响应实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVo {

    @Schema(description = "商品id")
    private Long id;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "品牌id")
    private Long brandId;

    @Schema(description = "品牌名称")
    private String brandName;

    @Schema(description = "轮播图")
    private String carouselUrl;

    @Schema(description = "一级分类")
    private Long categoryId1;

    @Schema(description = "二级分类")
    private Long categoryId2;

    @Schema(description = "三级分类")
    private Long categoryId3;

    @Schema(description = "一级分类名称")
    private String categoryName1;

    @Schema(description = "二级分类名称")
    private String categoryName2;

    @Schema(description = "三级分类名称")
    private String categoryName3;

    @Schema(description = "计量单位")
    private String unitName;

    @Schema(description = "商品规格id")
    private Long productSpecId;

    @Schema(description = "商品规格信息")
    private String productSpecInfo;

    @Schema(description = "创建人id")
    private Long createUserId;

    @Schema(description = "创建人名称")
    private String createUserName;

    @Schema(description = "审核人id")
    private Long reviewUserId;

    @Schema(description = "审核人名称")
    private String reviewUserName;

    @Schema(description = "审核状态：0-未审核，1-通过，2-驳回")
    private Integer reviewStatus;

    @Schema(description = "审核信息")
    private String reviewInfo;

    @Schema(description = "商品SKU信息")
    private List<ProductSkuVo> productSkuList;

    @Schema(description = "商品详情信息")
    private ProductDetailVo productDetail;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
