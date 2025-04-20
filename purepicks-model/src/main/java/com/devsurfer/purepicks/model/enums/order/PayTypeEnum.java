package com.devsurfer.purepicks.model.enums.order;

import com.devsurfer.purepicks.model.enums.annocation.IntegerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/4/20 1:01
 * description TODO
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum implements IntegerEnum {

    WE_CHAT(1, "微信"),
    ALI_PAY(2, "支付宝")
    ;

    private final Integer value;

    private final String desc;

    @Override
    public Integer value() {
        return value;
    }
}
