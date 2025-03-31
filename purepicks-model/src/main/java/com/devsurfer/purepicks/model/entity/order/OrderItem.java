package com.devsurfer.purepicks.model.entity.order;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Tag(name = "订单子项实体", description = "订单子项实体")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @Schema(description = "订单id")
    private Long orderId;

    @Schema(description = "商品skuId")
    private Long skuId;

    @Schema(description = "商品Sku名称")
    private String skuName;

    @Schema(description = "商品Sku图片")
    private String thumbImg;

    @Schema(description = "商品Sku价格")
    private BigDecimal skuPrice;

    @Schema(description = "商品Sku数量")
    private Integer skuNum;

    public BigDecimal computerPrice() {
        return skuPrice.multiply(BigDecimal.valueOf(skuNum));
    }

}
