package com.devsurfer.purepicks.manager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "线程池监控接口")
@RestController
@RequestMapping("/admin/thread")
@AllArgsConstructor
public class ThreadPoolMonitorController {

    private final ThreadPoolTaskExecutor ioTaskExecutor;
    private final ThreadPoolTaskExecutor taskExecutor;

    @GetMapping("/cpu-pool-status")
    @Operation(summary = "查询-cpu线程池状态")
    public String getThreadCpuPoolStatus() {
        return String.format(
                "CPU Core Size %d, Max Size %d, Pool Size: %d, Active Threads: %d, Queue Size: %d, Completed Tasks: %d",
                taskExecutor.getCorePoolSize(),
                taskExecutor.getMaxPoolSize(),
                taskExecutor.getPoolSize(),
                taskExecutor.getActiveCount(),
                taskExecutor.getQueueSize(),
                taskExecutor.getThreadPoolExecutor().getCompletedTaskCount()
        );
    }

    @GetMapping("/io-pool-status")
    @Operation(summary = "查询-io线程池状态")
    public String getThreadIoPoolStatus() {
        return String.format(
                "CPU Core Size %d, Max Size %d, Pool Size: %d, Active Threads: %d, Queue Size: %d, Completed Tasks: %d",
                ioTaskExecutor.getCorePoolSize(),
                ioTaskExecutor.getMaxPoolSize(),
                ioTaskExecutor.getPoolSize(),
                ioTaskExecutor.getActiveCount(),
                ioTaskExecutor.getQueueSize(),
                ioTaskExecutor.getThreadPoolExecutor().getCompletedTaskCount()
        );
    }

    @GetMapping("/adjust-io-pool")
    @Operation(summary = "临时更新-IO线程池参数")
    public String adjustThreadIoPool(@RequestParam int corePoolSize, @RequestParam int maxPoolSize) {
        // 确保线程池未关闭
        if (ioTaskExecutor.getThreadPoolExecutor().isShutdown()) {
            return "Thread pool is already shutdown";
        }

        // 确保参数合法性
        if (corePoolSize < 0 || corePoolSize > maxPoolSize) {
            return "Invalid parameters: corePoolSize=" + corePoolSize + ", maxPoolSize=" + maxPoolSize;
        }
        ioTaskExecutor.setMaxPoolSize(maxPoolSize);
        ioTaskExecutor.setCorePoolSize(corePoolSize);
        return "IO Thread pool adjusted: corePoolSize=" + corePoolSize + ", maxPoolSize=" + maxPoolSize;
    }

    @GetMapping("/adjust-cpu-pool")
    @Operation(summary = "临时更新-CPU线程池参数")
    public String adjustThreadCpuPool(@RequestParam int corePoolSize, @RequestParam int maxPoolSize) {
        // 确保线程池未关闭
        if (taskExecutor.getThreadPoolExecutor().isShutdown()) {
            return "Thread pool is already shutdown";
        }

        // 确保参数合法性
        if (corePoolSize < 0 || corePoolSize > maxPoolSize) {
            return "Invalid parameters: corePoolSize=" + corePoolSize + ", maxPoolSize=" + maxPoolSize;
        }
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setCorePoolSize(corePoolSize);
        return "CPU Thread pool adjusted: corePoolSize=" + corePoolSize + ", maxPoolSize=" + maxPoolSize;
    }
}
