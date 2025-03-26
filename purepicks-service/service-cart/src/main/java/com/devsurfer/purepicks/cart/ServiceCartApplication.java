package com.devsurfer.purepicks.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/25 21:42
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.devsurfer.purepicks")
public class ServiceCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCartApplication.class, args);
    }

}
