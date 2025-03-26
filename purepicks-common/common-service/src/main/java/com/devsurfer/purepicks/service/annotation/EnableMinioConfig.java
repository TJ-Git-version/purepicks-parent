package com.devsurfer.purepicks.service.annotation;

import com.devsurfer.purepicks.model.properties.MinioProperties;
import com.devsurfer.purepicks.service.config.MinioConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/25 21:52
 * description TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Import({MinioConfig.class, MinioProperties.class})
public @interface EnableMinioConfig {
}
