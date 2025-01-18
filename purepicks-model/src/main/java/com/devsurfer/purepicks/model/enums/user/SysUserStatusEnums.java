package com.devsurfer.purepicks.model.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 23:49
 * description TODO
 */
@Getter
@AllArgsConstructor
public enum SysUserStatusEnums {

    USER_BANNED(0, "冻结用户"),
    USER_NORMAL(1, "用户解冻");

    private final Integer status;
    private final String message;

}
