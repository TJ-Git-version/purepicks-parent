package com.devsurfer.purepicks.model.vo.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dev Surfer
 */
@Data
public class Product implements Serializable {

    @Schema(description = "商品id")
    private Long id;

    @Schema(description = "商品名称")
    private String name;

    @Schema(description = "品牌id")
    private Long brandId;

    @Schema(description = "品牌名称")
    private String brandName;

    @Schema(description = "轮播图")
    private String sliderUrls;

    @Schema(description = "一级分类")
    private Long category1Id;

    @Schema(description = "二级分类")
    private Long category2Id;

    @Schema(description = "三级分类")
    private Long category3Id;

    @Schema(description = "一级分类名称")
    private String category1Name;

    @Schema(description = "二级分类名称")
    private String category2Name;

    @Schema(description = "三级分类名称")
    private String category3Name;

    @Schema(description = "计量单位")
    private String unitName;

    @Schema(description = "商品规格id")
    private Long productSpecId;

    @Schema(description = "商品规格信息")
    private String specValue;

    @Schema(description = "创建人id")
    private Long createUserId;

    @Schema(description = "创建人名称")
    private String createUserName;

    @Schema(description = "审核状态：0-未审核，1-通过，2-驳回")
    private Integer auditStatus;

    @Schema(description = "审核信息")
    private String auditMessage;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;


}
