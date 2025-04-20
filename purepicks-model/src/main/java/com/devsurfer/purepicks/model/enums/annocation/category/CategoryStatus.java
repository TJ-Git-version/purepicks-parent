package com.devsurfer.purepicks.model.enums.annocation.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/9 20:19
 * description TODO
 */
@Getter
@AllArgsConstructor
public enum CategoryStatus {
    // 不可见;

    INVISIBLE(0, "不可见"), // 可见;
    VISIBLE(1, "可见");

    private final Integer code;
    private final String message;

    public static CategoryStatus getByCode(Integer code) {
        for (CategoryStatus status : CategoryStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    public static CategoryStatus getByMessage(String message) {
        for (CategoryStatus status : CategoryStatus.values()) {
            if (status.getMessage().equals(message)) {
                return status;
            }
        }
        return CategoryStatus.INVISIBLE;
    }
}
