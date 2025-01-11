package com.devsurfer.purepicks.model.enums;

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
    LOGIN_REFRESH_TOKEN("user:login:refreshToken:{}");

    private final String key;

    public static String build(RedisKeyConstant redisKeyConstant, String keySuffix) {
        return StrUtil.format(redisKeyConstant.getKey(), keySuffix);
    }

}
