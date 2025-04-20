package com.devsurfer.purepicks.cart;

import com.devsurfer.purepicks.service.annotation.EnableUserTokenFeignInterceptor;
import com.devsurfer.purepicks.service.annotation.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/25 21:42
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.devsurfer.purepicks")
@EnableFeignClients(basePackages = {
        "com.devsurfer.purepicks.feign.product"
})
@EnableUserWebMvcConfiguration
@EnableUserTokenFeignInterceptor
public class ServiceCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCartApplication.class, args);
    }

}
