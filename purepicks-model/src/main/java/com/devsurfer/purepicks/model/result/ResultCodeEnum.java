package com.devsurfer.purepicks.model.result;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/9 22:00
 * description 封装统一结果集枚举
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功");

    private final Integer code;

    private final String message;

}
