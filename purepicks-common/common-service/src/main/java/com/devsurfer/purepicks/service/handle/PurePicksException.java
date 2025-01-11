package com.devsurfer.purepicks.service.handle;

import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 19:24
 * description 自定义异常类
 */
@Getter
public class PurePicksException extends RuntimeException {

    private final Integer code;

    public PurePicksException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public PurePicksException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public static void error(ResultCodeEnum resultCodeEnum) {
        throw new PurePicksException(resultCodeEnum);
    }

}
