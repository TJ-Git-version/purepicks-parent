package com.devsurfer.purepicks.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
        System.out.println("http://localhost:8200/doc.html");
    }
}
