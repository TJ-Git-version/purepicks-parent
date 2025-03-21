package com.devsurfer.purepicks.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 14:03
 * description knife4j接口文档配置
 */
@Configuration
public class Knife4jConfig {

    /**
     * aip分组：区分不同访问的接口文档内容
     * 后台管理接口文档
     * 使用配置文件替代以下配置：约定大于配置
     */
    @Bean
    public GroupedOpenApi apiManager() {
        return GroupedOpenApi.builder()
                .group("api-manager") // 分组名称
                .pathsToMatch("/admin/**") // 匹配路径
                .packagesToScan("com.devsurfer.purepicks.manager.controller") // 指定handler处理器路径
                .build();
    }

    /**
     * H5端接口文档
     */
    @Bean
    public GroupedOpenApi webApi() {
        return GroupedOpenApi.builder()
                .group("web-api")
                .pathsToMatch("/api/**")
                .build();
    }

    /**
     * 自定义api基本信息
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("甄选精选API接口文档") // 接口文档标题
                                .version("1.0") // 项目迭代版本
                                .description("提供接口访问路径,传参要求,实体类解析") // 接口文档描述
                                .contact(new Contact().name("devSurfer").url("").email("2929408642@qq.com")) // 项目作者
                );
    }

}
