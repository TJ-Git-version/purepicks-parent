package com.devsurfer.purepicks.model.enums.redis;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:44
 * description TODO
 */
@Getter
@AllArgsConstructor
public enum RedisKeyConstantEnum {

    LOGIN_TOKEN("user:login:token:{}"),
    LOGIN_REFRESH_TOKEN("user:login:refreshToken:{}"),
    LOGIN_VALIDATE_CODE("user:login:validateCode:{}"),

    APPLET_CATEGORY_TREE("applet:category:tree"),
    APPLET_CATEGORY_TOP("applet:category:top"),
    APPLET_LOGIN_TOKEN("applet:login:token:{}"),

    MANAGER_CATEGORY_TREE("manager:category:tree"),

    PHONE_VALIDATE_CODE("phone:code:validateCode:{}"),
    ;

    private final String key;

    public static String build(RedisKeyConstantEnum redisKeyConstant, String keySuffix) {
        return StrUtil.format(redisKeyConstant.getKey(), keySuffix);
    }

}
