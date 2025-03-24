package com.devsurfer.purepicks.service.annotation;

import com.devsurfer.purepicks.service.config.UserWebMvcConfiguration;
import com.devsurfer.purepicks.service.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/24 22:39
 * description TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({UserLoginAuthInterceptor.class, UserWebMvcConfiguration.class})
public @interface EnableUserWebMvcConfiguration {
}
