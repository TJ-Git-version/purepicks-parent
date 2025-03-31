package com.devsurfer.purepicks.order;

import com.devsurfer.purepicks.service.annotation.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/27 22:27
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.devsurfer.purepicks.feign.cart")
@EnableUserTokenFeignInterceptor
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }

}
