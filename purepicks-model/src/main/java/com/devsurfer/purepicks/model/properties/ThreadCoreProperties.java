package com.devsurfer.purepicks.model.properties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池参数配置类
 *
 * @author Dev Surfer
 */
@Data
@ConfigurationProperties(prefix = "thread.core")
public class ThreadCoreProperties {

    @Schema(description = "cpu线程池配置类")
    private CpuProperties cpuProperties;

    @Schema(description = "io线程池配置类")
    private IoProperties ioProperties;

    public ThreadCoreProperties() {
        this.cpuProperties = new CpuProperties();
        this.ioProperties = new IoProperties();
    }

    @Data
    public static class CpuProperties {

        @Schema(description = "核心线程数")
        private int corePoolSize;

        @Schema(description = "最线程数")
        private int maxPoolSize;

        @Schema(description = "任务队列容量")
        private int queueCapacity;

        @Schema(description = "线程空闲时间为")
        private int keepAliveSeconds;

        @Schema(description = "线程前缀")
        private String threadNamePrefix;

    }

    @Data
    public static class IoProperties {

        @Schema(description = "核心线程数")
        private int corePoolSize;

        @Schema(description = "最线程数")
        private int maxPoolSize;

        @Schema(description = "任务队列容量")
        private int queueCapacity;

        @Schema(description = "线程空闲时间为")
        private int keepAliveSeconds;

        @Schema(description = "线程前缀")
        private String threadNamePrefix;

    }

}
