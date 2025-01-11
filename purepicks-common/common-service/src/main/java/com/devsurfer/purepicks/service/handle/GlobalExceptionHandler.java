package com.devsurfer.purepicks.service.handle;

import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.result.ResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 18:51
 * description 全局统一异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常捕获
     */
    @ExceptionHandler(PurePicksException.class)
    public ResultUtil<?> PurePicksExceptionHandler(PurePicksException exception) {
        return ResultUtil.error(exception.getCode(), exception.getMessage());
    }

    /**
     * 全局异常捕获，兜底异常
     */
    @ExceptionHandler(Exception.class)
    public ResultUtil<?> exceptionHandler(Exception exception) {
        return ResultUtil.error(ResultCodeEnum.SYSTEM_ERROR);
    }

}
