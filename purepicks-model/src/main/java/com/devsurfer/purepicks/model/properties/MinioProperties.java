package com.devsurfer.purepicks.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/14 23:49
 * description TODO
 */
@Data
@ConfigurationProperties(prefix = "purepicks.minio") // 前缀不能使用驼峰命名
public class MinioProperties {

    // 上传minio服务器地址
    private String endpoint;

    // 端口
    private Integer port;

    // 登录账号
    private String accessKey;

    // 登录密码
    private String secretKey;

    // 存储桶
    private String bucket;
}
