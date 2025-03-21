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
public enum RedisKeyConstant {

    LOGIN_TOKEN("user:login:token:{}"),
    LOGIN_REFRESH_TOKEN("user:login:refreshToken:{}"),
    LOGIN_VALIDATE_CODE("user:login:validateCode:{}"),
    APPLET_CATEGORY_ONE("applet:category:one");

    private final String key;

    public static String build(RedisKeyConstant redisKeyConstant, String keySuffix) {
        return StrUtil.format(redisKeyConstant.getKey(), keySuffix);
    }

}
