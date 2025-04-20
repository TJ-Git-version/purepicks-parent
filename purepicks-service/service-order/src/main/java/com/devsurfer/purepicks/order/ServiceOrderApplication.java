package com.devsurfer.purepicks.order;

import com.devsurfer.purepicks.service.annotation.EnableUserTokenFeignInterceptor;
import com.devsurfer.purepicks.service.annotation.EnableUserWebMvcConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/27 22:27
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.devsurfer.purepicks.feign.cart",
        "com.devsurfer.purepicks.feign.user",
        "com.devsurfer.purepicks.feign.product"
})
@ComponentScan("com.devsurfer.purepicks")
@EnableUserWebMvcConfiguration
@EnableUserTokenFeignInterceptor
@MapperScan("com.devsurfer.purepicks.order.mapper")
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }

}
