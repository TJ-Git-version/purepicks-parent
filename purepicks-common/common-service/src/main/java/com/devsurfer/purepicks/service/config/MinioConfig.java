package com.devsurfer.purepicks.service.config;

import com.devsurfer.purepicks.model.properties.MinioProperties;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/14 23:43
 * description Minio配置类
 */
public class MinioConfig {
    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) {
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint(), minioProperties.getPort(), false)
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

}
