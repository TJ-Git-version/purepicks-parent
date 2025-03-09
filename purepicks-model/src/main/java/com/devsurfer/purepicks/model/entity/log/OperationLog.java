package com.devsurfer.purepicks.model.entity.log;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import com.devsurfer.purepicks.model.enums.log.LogBusinessTypeEnum;
import com.devsurfer.purepicks.model.enums.log.LogOperateStatusEnum;
import com.devsurfer.purepicks.model.enums.log.LogOperateTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "操作日志", description = "操作日志")
public class OperationLog extends BaseEntity {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "模块名称")
    private String moduleName;

    @Schema(description = "方法名称")
    private String methodName;

    @Schema(description = "方法类路径")
    private String methodClassPath;

    @Schema(description = "业务类型")
    private LogBusinessTypeEnum businessType;

    @Schema(description = "操作类型")
    private LogOperateTypeEnum operateType;

    @Schema(description = "请求方式")
    private String requestMethod;

    @Schema(description = "操作人员 ID")
    private Long operateId;

    @Schema(description = "操作人员名称")
    private String operateName;

    @Schema(description = "请求url")
    private String requestUrl;

    @Schema(description = "IP地址")
    private String requestIp;

    @Schema(description = "请求参数")
    private String requestParam;

    @Schema(description = "响应结果")
    private String responseResult;

    @Schema(description = "操作状态（0-正常，1-异常）")
    private LogOperateStatusEnum operateStatus;

    @Schema(description = "错误消息")
    private String errorMsg;

    @Schema(description = "操作时间")
    private Date operateTime;

}
