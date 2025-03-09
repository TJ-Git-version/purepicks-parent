package com.devsurfer.purepicks.model.enums.log;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Tag(name = "日志操作类型枚举")
public enum LogOperateTypeEnum {

    @Schema(description = "其他")
    OTHER(0, "其他"),

    @Schema(description = "后台用户")
    MANAGER_USER(1, "后台用户"),

    @Schema(description = "手机端用户")
    MOBILE_USER(2, "手机端用户");

    private final Integer type;

    private final String message;

}
