package com.devsurfer.purepicks.log.annotation;

import com.devsurfer.purepicks.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/9 14:58
 * description TODO
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LogAspect.class) // 通过Import注解导入日志切面类到Spring容器中
public @interface EnableLogAspect {

}
