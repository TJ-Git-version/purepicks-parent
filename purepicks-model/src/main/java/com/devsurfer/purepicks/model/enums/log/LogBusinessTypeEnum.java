package com.devsurfer.purepicks.model.enums.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "日志操作类型枚举")
public enum LogBusinessTypeEnum {

    @Schema(description = "其他")
    OTHER(0, "其他"),

    @Schema(description = "新增")
    INSERT(1, "新增"),

    @Schema(description = "修改")
    UPDATE(2, "修改"),

    @Schema(description = "删除")
    DELETE(3, "删除");

    private final Integer type;

    private final String message;

}
