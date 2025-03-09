package com.devsurfer.purepicks.log.annotation;

import com.devsurfer.purepicks.model.enums.log.LogBusinessTypeEnum;
import com.devsurfer.purepicks.model.enums.log.LogModuleNameEnum;
import com.devsurfer.purepicks.model.enums.log.LogOperateTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    // 模块名称
    LogModuleNameEnum moduleName();

    // 操作人类别
    LogOperateTypeEnum operateType() default LogOperateTypeEnum.OTHER;

    // 业务类型
    LogBusinessTypeEnum businessType() default LogBusinessTypeEnum.OTHER;

    // 是否保留请求的参数
    boolean isSaveRequestData() default true;

    // 是否保存响应的参数
    boolean isSaveResponseData() default true;

}
