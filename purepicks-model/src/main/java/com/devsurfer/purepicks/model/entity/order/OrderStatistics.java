package com.devsurfer.purepicks.model.entity.order;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Tag(name = "订单统计表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatistics extends BaseEntity {

    @Schema(description = "订单统计日期")
    private Date orderDate;

    @Schema(description = "订单统计金额")
    private BigDecimal totalAmount;

    @Schema(description = "订单总量")
    private Integer totalNum;

}
