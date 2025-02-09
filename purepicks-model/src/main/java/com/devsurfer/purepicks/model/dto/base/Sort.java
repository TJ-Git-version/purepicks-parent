package com.devsurfer.purepicks.model.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/25 20:00
 * description 自定义升序降序逻辑抽取
 */
@Data
@Slf4j
@Tag(name = "自定义升序降序逻辑抽取")
public class Sort {

    @Schema(description = "排序规则")
    private String order;

    @Schema(description = "排序字段")
    private String column;

    @Schema(description = "排序字段所在类，防止sql注入风险")
    private String className;

    public Sort() {
        try {
            Field field = this.className.getClass().getDeclaredField(column);
            this.column = field.getName();
        } catch (NoSuchFieldException e) {
            log.info("排序规则实体解析错误,有sql注入风险");
            throw new RuntimeException("排序规则实体解析错误,有sql注入风险");
        }
    }

}
