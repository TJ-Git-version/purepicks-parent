package com.devsurfer.purepicks.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/9 22:56
 * description 程序入口
 */
@SpringBootApplication
@MapperScan("com.devsurfer.purepicks.manager.mapper")
public class PurePicksManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurePicksManagerApplication.class, args);
    }

}
