package com.devsurfer.purepicks.service.config;

import com.devsurfer.purepicks.service.interceptor.UserLoginAuthInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/24 22:37
 * description 拦截器注册: 注册用户登录拦截器
 */
public class UserWebMvcConfiguration implements WebMvcConfigurer {

    private final UserLoginAuthInterceptor userLoginAuthInterceptor;

    public UserWebMvcConfiguration(UserLoginAuthInterceptor userLoginAuthInterceptor) {
        this.userLoginAuthInterceptor = userLoginAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLoginAuthInterceptor)
                .addPathPatterns("/api/**");
    }
}
