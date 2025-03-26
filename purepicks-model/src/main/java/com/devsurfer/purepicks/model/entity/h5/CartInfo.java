package com.devsurfer.purepicks.model.entity.h5;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/26 20:59
 * description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "购物车实体类")
public class CartInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "skuId")
    private Long skuId;

    @Schema(description = "放入购物车时价格")
    private BigDecimal cartPrice;

    @Schema(description = "数量")
    private Integer skuNum;

    @Schema(description = "图片资源")
    private String imgUrl;

    @Schema(description = "sku名称(冗余)")
    private String skuName;

    @Schema(description = "isChecked")
    private Integer isChecked;

}
