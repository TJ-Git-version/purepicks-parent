package com.devsurfer.purepicks.model.vo.h5;

import com.devsurfer.purepicks.model.entity.order.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/30 22:31
 * description 结算响应实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "结算响应实体类")
public class TradeVo {

    @Schema(description = "结算总金额")
    private BigDecimal totalAmount;

    @Schema(description = "结算商品列表")
    private List<OrderItem> orderItemList;

}
