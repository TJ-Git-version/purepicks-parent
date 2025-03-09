package com.devsurfer.purepicks.log.aspect;

import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.log.annotation.Log;
import com.devsurfer.purepicks.log.service.AsyncOperationLogService;
import com.devsurfer.purepicks.model.entity.log.OperationLog;
import com.devsurfer.purepicks.model.enums.log.LogOperateStatusEnum;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import com.devsurfer.purepicks.utils.IpAddressUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/9 14:53
 * description 日志切面类
 */
@Aspect
@Component
@AllArgsConstructor
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private final AsyncOperationLogService asyncOperationLogService;

    @Around(value = "@annotation(sysLog)")
    public Object logAround(ProceedingJoinPoint joinPoint, Log sysLog) {
        OperationLog operationLog = new OperationLog();
        beforeHandleLog(joinPoint, sysLog, operationLog);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            afterHandleLog(proceed, sysLog, operationLog, LogOperateStatusEnum.SUCCESS, "");
        } catch (Throwable e) {
            // 业务异常执行
            afterHandleLog(proceed, sysLog, operationLog, LogOperateStatusEnum.FAIL, e.getMessage());
            logger.info("业务异常：{}", e.getMessage());
            throw new RuntimeException();
        } finally {
            asyncOperationLogService.saveOperationLog(operationLog);
        }
        return proceed;
    }

    /**
     * 请求日志后置处理
     * @param proceed                  响应体内容
     * @param sysLog                   日志注解类
     * @param operationLog             日志记录类
     */
    private void afterHandleLog(Object proceed, Log sysLog, OperationLog operationLog, LogOperateStatusEnum operateStatus, String errorMsg) {
        if (sysLog.isSaveResponseData()) {
            operationLog.setResponseResult(JSONUtil.toJsonStr(proceed));
        }
        operationLog.setOperateStatus(operateStatus);
        operationLog.setErrorMsg(errorMsg);
    }

    /**
     * 请求日志前置处理
     * @param joinPoint               切面代理类
     * @param sysLog                  日志注解类
     * @param operationLog            日志记录类
     */
    private void beforeHandleLog(ProceedingJoinPoint joinPoint, Log sysLog, OperationLog operationLog) {
        // 模块名称
        operationLog.setModuleName(sysLog.moduleName().getValue());
        // 业务类型
        operationLog.setBusinessType(sysLog.businessType());
        // 操作类别
        operationLog.setOperateType(sysLog.operateType());
        // 用户id/名称
        operationLog.setOperateId(AuthContextUtil.getUserId());
        operationLog.setOperateName(AuthContextUtil.get().getUsername());
        // 操作时间
        operationLog.setOperateTime(new Date());

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 方法名称
        operationLog.setMethodName(method.getName());
        // 方法类路径
        operationLog.setMethodClassPath(method.getDeclaringClass().getName());

        // 请求相关参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            // 获取请求体
            HttpServletRequest request = requestAttributes.getRequest();
            operationLog.setRequestMethod(request.getMethod());
            operationLog.setRequestUrl(request.getRequestURI());
            operationLog.setRequestIp(IpAddressUtil.getIpAddress(request));
            // 设置请求参数
            if (sysLog.isSaveRequestData()) {
                String requestMethod = operationLog.getRequestMethod();
                if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
                    operationLog.setRequestParam(argsArrayToString(joinPoint.getArgs()));
                }
            }
        }
    }

    /**
     * 参数转字符串, 涉及过滤 multipartFile | request | response 对象/子类对象过滤
     * @param paramsArray               参数数组
     * @return                          参数封装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object paramObj : paramsArray) {
                if (paramObj != null && !isFilterObject(paramObj)) {
                    try {
                        params.append(JSONUtil.toJsonStr(paramObj));
                    } catch (Exception e) {
                        logger.error("请求参数解析失败：{}", e.getMessage());
                    }
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象
     *
     * @param paramObj          对象消息
     * @return 如果需要过滤对象, 则返回true，否则返回false
     */
    private boolean isFilterObject(Object paramObj) {
        Class<?> clazz = paramObj.getClass();
        if (clazz.isArray()) { // 判断是否为数组类型
            // 如果是数组,则判断数组中的元素是否为文件类型(multipartFile) 或其子类
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) { // 判断是否为集合类型
            Collection<?> collection = (Collection<?>) paramObj;
            for (Object item : collection) {
                // 只要有一个元素的类型为MultipartFile或其子类, 则认为该集合对象为过滤对象
                return item instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) { // 判断是否为Map类型
            Map<?, ?> map = (Map<?, ?>) paramObj;
            for (Map.Entry<?, ?> value : map.entrySet()) {
                // 只要有一个Value的类型是MultipartFile或其子类，则认为该映射对象为过滤对象
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) value.getValue();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        // 如果以上条件都不满足，最后判断对象本身是否为MultipartFile、HttpServletRequest、HttpServletResponse类的实例
        return paramObj instanceof MultipartFile || paramObj instanceof HttpServletRequest || paramObj instanceof HttpServletResponse;
    }
}
