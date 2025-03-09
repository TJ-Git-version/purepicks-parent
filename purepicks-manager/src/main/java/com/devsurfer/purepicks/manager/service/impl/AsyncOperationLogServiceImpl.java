package com.devsurfer.purepicks.manager.service.impl;

import com.devsurfer.purepicks.log.service.AsyncOperationLogService;
import com.devsurfer.purepicks.manager.mapper.OperationLogMapper;
import com.devsurfer.purepicks.model.entity.log.OperationLog;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/9 16:30
 * description TODO
 */
@Service
@AllArgsConstructor
public class AsyncOperationLogServiceImpl implements AsyncOperationLogService {

    private final OperationLogMapper operationLogMapper;

    @Async
    @Override
    public void saveOperationLog(OperationLog operationLog) {
        operationLogMapper.insertLog(operationLog);
    }
}
