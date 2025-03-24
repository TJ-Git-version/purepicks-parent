package com.devsurfer.purepicks.product;

import com.devsurfer.purepicks.service.annotation.EnableUserWebMvcConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/11 23:15
 * description TODO
 */
@SpringBootApplication
@MapperScan(basePackages = "com.devsurfer.purepicks.product.mapper")
@ComponentScan("com.devsurfer.purepicks")
@EnableCaching
@EnableUserWebMvcConfiguration
public class ServiceProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class, args);
        System.out.println("http://localhost:8100/doc.html");
    }

}
