package com.devsurfer.purepicks.user;

import com.devsurfer.purepicks.service.annotation.EnableMinioConfig;
import com.devsurfer.purepicks.service.annotation.EnableUserWebMvcConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 19:29
 * description 用户中心服务启动器
 */
@SpringBootApplication
@MapperScan("com.devsurfer.purepicks.user.mapper")
@ComponentScan(basePackages = "com.devsurfer.purepicks")
@EnableUserWebMvcConfiguration
@EnableMinioConfig
public class ServiceUserApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServiceUserApplication.class, args);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("http://localhost:8200/doc.html");
    }
}
