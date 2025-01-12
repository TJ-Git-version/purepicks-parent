package com.devsurfer.purepicks.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 14:57
 * description TODO
 */
@Data
@ConfigurationProperties(prefix = "purepicks.auth")
public class UserAuthProperties {

    private List<String> noAuthUrls;

}
