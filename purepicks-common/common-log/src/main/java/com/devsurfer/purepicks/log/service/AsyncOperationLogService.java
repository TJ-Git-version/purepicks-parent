package com.devsurfer.purepicks.log.service;

import com.devsurfer.purepicks.model.entity.log.OperationLog;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/9 16:28
 * description TODO
 */
public interface AsyncOperationLogService {

    void saveOperationLog(OperationLog operationLog);

}
