package com.devsurfer.purepicks.manager.config;

import com.devsurfer.purepicks.manager.interceptor.LoginInterceptor;
import com.devsurfer.purepicks.manager.properties.UserAuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 1:16
 * description WebMvcConfigurer配置类
 */
@Configuration
@EnableConfigurationProperties(UserAuthProperties.class)
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    private final UserAuthProperties userAuthProperties;

    public WebMvcConfiguration(LoginInterceptor loginInterceptor, UserAuthProperties userAuthProperties) {
        this.loginInterceptor = loginInterceptor;
        this.userAuthProperties = userAuthProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 添加路径规则
                .allowCredentials(true)         // 是否允许跨域请求的情况下传递cookie
                .allowedOriginPatterns("*")     // 允许请求来源的域规则
                .allowedMethods("*")            // 允许请求方式
                .allowedHeaders("*");           // 允许携带的请求头
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns(userAuthProperties.getNoAuthUrls()).addPathPatterns("/**");
    }
}
