package com.devsurfer.purepicks.model.dto.h5;

import com.devsurfer.purepicks.model.entity.order.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/31 23:16
 * description 提交订单实体类
 */
@Data
public class OrderInfoDto implements Serializable {

    @Schema(description = "发货地址ID")
    private Long userAddressId;

    @Schema(description = "订单明细")
    private List<OrderItem> orderItemList;

    @Schema(description = "运费")
    private BigDecimal feightFee;

    @Schema(description = "备注")
    private String remark;

}
