package com.devsurfer.purepicks.model.enums.order;

import com.devsurfer.purepicks.model.enums.annocation.IntegerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/4/20 1:08
 * description TODO
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements IntegerEnum {
    PENDING_PAYMENT(0, "待付款"),
    TO_SHIPPED(1, "待发货"),
    SHIPPED(2, "已发货"),
    TO_RECEIVED(3, "待用户收货, 已完成"),
    CANCELED(-1, "已取消"),
    ;
    private final Integer value;

    private final String desc;

    @Override
    public Integer value() {
        return value;
    }
}
