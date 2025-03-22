package com.devsurfer.purepicks.service.config;

import cn.hutool.core.util.StrUtil;
import com.devsurfer.purepicks.model.properties.ThreadCoreProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池配置
 * - CPU 密集型任务
 * - IO  密集型任务
 *
 * @author Dev Surfer
 */
@Configuration
@EnableConfigurationProperties(ThreadCoreProperties.class)
public class AsyncConfig {

    /**
     * 覆盖spring默认线程池配置, CPU 密集型任务线程池
     *      核心线程数: CPU 核心数是线程池的理想大小，可以充分利用 CPU 资源。额外的 1 个线程用于处理突发任务或线程阻塞的情况。
     *      最大线程数: CPU 密集型任务不需要过多的线程，过多的线程会导致上下文切换开销。保持最大线程数与核心线程数一致，避免线程数动态扩展。
     *      任务队列容量: CPU 密集型任务应尽快执行，避免任务在队列中堆积。如果队列容量过大，任务可能会在队列中等待较长时间，降低响应速度。
     *      线程空闲时间: CPU 密集型任务的线程池大小固定，不需要回收线程。
     *      拒绝策略: 当线程池和队列都满时，由调用线程执行任务，避免任务丢失。适用于 CPU 密集型任务，因为调用线程通常是主线程或工作线程，可以分担部分计算任务。
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor cpuTaskExecutor(ThreadCoreProperties coreProperties) {
        ThreadCoreProperties.CpuProperties cpuProperties = coreProperties.getCpuProperties();
        // 获取 CPU 核心线程数
        int cpuCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数 = CPU 核心数 + 1
        executor.setCorePoolSize(cpuProperties.getCorePoolSize() != 0 ? cpuProperties.getCorePoolSize() : cpuCores + 1);
        // 最大线程数 = CPU 核心数 + 1
        executor.setMaxPoolSize(cpuProperties.getCorePoolSize() != 0 ? cpuProperties.getCorePoolSize() : cpuCores + 1);
        // 任务队列容量为 0
        executor.setQueueCapacity(cpuProperties.getQueueCapacity() != 0 ? cpuProperties.getQueueCapacity() : 0);
        // 线程空闲时间为 0（不回收核心线程）
        executor.setKeepAliveSeconds(cpuProperties.getKeepAliveSeconds() != 0 ? cpuProperties.getKeepAliveSeconds() : 0);
        // 线程前缀
        executor.setThreadNamePrefix(StrUtil.isNotBlank(cpuProperties.getThreadNamePrefix()) ? cpuProperties.getThreadNamePrefix() : "Cpu-Task-");
        // 拒绝策略-堵塞线程,不丢失线程,适用于高并发少的场景
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * IO 密集型任务线程池
     *      核心线程数: I/O 密集型任务的线程在等待 I/O 时不会占用 CPU，因此可以适当增加线程数。通过增加线程数，可以充分利用 I/O 等待时间，提高并发性能。
     *      最大线程数: I/O 密集型任务的线程在等待 I/O 时不会占用 CPU，因此可以支持更多的并发线程。最大线程数应根据系统的负载和资源限制进行调整。
     *      任务队列: 如果任务量较大，队列可以缓冲任务，避免任务丢失。队列容量应根据系统的负载能力和任务量进行调整。
     *      线程空闲时间: I/O 密集型任务的线程池可能会动态扩展，空闲线程需要在一定时间后被回收，以释放资源。
     *      拒绝策略: 当线程池和队列都满时，由调用线程执行任务，避免任务丢失。
     */
    @Bean("ioTaskExecutor")
    public ThreadPoolTaskExecutor ioTaskExecutor(ThreadCoreProperties coreProperties) {
        ThreadCoreProperties.IoProperties ioProperties = coreProperties.getIoProperties();
        int cpuCores = Runtime.getRuntime().availableProcessors(); // 获取 CPU 核心数
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数 = 核心线程数 * 2
        executor.setCorePoolSize(ioProperties.getCorePoolSize() != 0 ? ioProperties.getCorePoolSize() : cpuCores * 2);
        // 最大线程数: 根据系统负载和资源限制进行调整
        executor.setMaxPoolSize(ioProperties.getMaxPoolSize() != 0 ? ioProperties.getMaxPoolSize() : 100);
        // 任务队列
        executor.setQueueCapacity(ioProperties.getQueueCapacity() != 0 ? ioProperties.getQueueCapacity() : 1000);
        // 线程空闲时间
        executor.setKeepAliveSeconds(ioProperties.getKeepAliveSeconds() != 0 ? ioProperties.getKeepAliveSeconds() : 60);
        // 线程名称前缀
        executor.setThreadNamePrefix(StrUtil.isNotBlank(ioProperties.getThreadNamePrefix()) ? ioProperties.getThreadNamePrefix() : "Io-Task-");
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
