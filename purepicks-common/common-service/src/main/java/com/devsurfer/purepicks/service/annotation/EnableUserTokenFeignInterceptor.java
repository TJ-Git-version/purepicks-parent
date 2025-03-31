package com.devsurfer.purepicks.service.annotation;

import com.devsurfer.purepicks.service.feign.UserTokenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/31 22:07
 * description 开启微服务用户token传递拦截器
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UserTokenFeignInterceptor.class)
public @interface EnableUserTokenFeignInterceptor {
}
