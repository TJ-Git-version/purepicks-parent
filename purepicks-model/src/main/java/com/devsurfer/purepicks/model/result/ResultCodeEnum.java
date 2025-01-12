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

    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(201, "系统异常"),
    PARAMETER_INVALID_ERROR(202, "参数非法"),

    /* 用户模块 260-300*/
    USER_USERNAME_NOT_EXITS_ERROR(260, "用户名不存在"),
    USER_USERNAME_FROZEN_ERROR(261, "当前用户已被冻结"),
    USER_PASSWORD_ERROR(262, "密码错误,请重新输入"),
    USER_LOGIN_VALIDATE_CODE_ERROR(263, "验证码错误，请重新输入"),
    USER_LOGIN_VALIDATE_CODE_EXPIRE_ERROR(264, "验证码已过期，请重新获取"),
    USER_NOT_LOGIN_ERROR(265, "令牌过期，请重新登陆"),
    ;

    private final Integer code;

    private final String message;

}
