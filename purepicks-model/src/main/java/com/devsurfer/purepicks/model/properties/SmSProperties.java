package com.devsurfer.purepicks.model.properties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 20:41
 * description 短信验证密钥
 */
@Data
@ConfigurationProperties(prefix = "ssm")
public class SmSProperties {

    @Schema(description = "短信服务端url")
    private String url;

    @Schema(description = "api路径")
    private String path;

    @Schema(description = "模板id")
    private String templateId;

    @Schema(description = "密钥键")
    private String appKey;

    @Schema(description = "密钥")
    private String appSecret;

    @Schema(description = "code")
    private String appCode;

}
