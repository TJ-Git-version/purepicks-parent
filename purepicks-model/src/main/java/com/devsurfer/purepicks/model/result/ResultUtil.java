package com.devsurfer.purepicks.model.result;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/9 22:00
 * description 封装统一结果集实体类
 */
@Getter
public class ResultUtil<T> implements Serializable {

    private final Integer code;

    private final String message;

    private final T data;

    public ResultUtil(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultUtil<T> ok() {
        return ok(null, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResultUtil<T> ok(T data, ResultCodeEnum resultCodeEnum) {
        return ResultUtil.build(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    public static <T> ResultUtil<T> error(ResultCodeEnum resultCodeEnum) {
        return ResultUtil.build(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), null);
    }

    public static <T> ResultUtil<T> error(Integer code, String message) {
        return ResultUtil.build(code, message, null);
    }

    private static <T> ResultUtil<T> build(Integer code, String message, T data) {
        return new ResultUtil<>(code, message, data);
    }

}
