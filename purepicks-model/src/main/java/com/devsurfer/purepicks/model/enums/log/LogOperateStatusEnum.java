package com.devsurfer.purepicks.model.enums.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/9 15:21
 * description 日志-操作状态枚举
 */
@Getter
@AllArgsConstructor
@Schema(description = "日志-操作状态枚举")
public enum LogOperateStatusEnum {

    SUCCESS("0", "成功"),
    FAIL("1", "异常");

    private final String value;
    private final String name;

}
