package com.devsurfer.purepicks.model.entity.order;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import com.devsurfer.purepicks.model.enums.log.LogOperateTypeEnum;
import com.devsurfer.purepicks.model.enums.order.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/4/20 18:40
 * description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLog extends BaseEntity {

    // 订单id
    private Long orderId;

    // 操作用户类型
    private Integer operateUser;

    // 订单状态
    private Integer processStatus;

    // 备注
    private String note;

    public static OrderLog creatLog(Long orderId, LogOperateTypeEnum operateTypeEnum, OrderStatusEnum orderStatusEnum) {
        final OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderId);
        orderLog.setOperateUser(operateTypeEnum.getType());
        orderLog.setProcessStatus(orderStatusEnum.getValue());
        orderLog.setNote("");
        return orderLog;
    }
}
