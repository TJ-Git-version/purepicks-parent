package com.devsurfer.purepicks.model.entity.log;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import com.devsurfer.purepicks.model.enums.log.LogBusinessTypeEnum;
import com.devsurfer.purepicks.model.enums.log.LogOperateTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    @Schema(description = "主机地址")
    private String requestIp;

    @Schema(description = "请求参数")
    private String requestParam;

    @Schema(description = "响应结果")
    private String responseResult;

    @Schema(description = "操作状态（0正常，1异常）")
    private Integer operateStatus;

    @Schema(description = "错误消息")
    private String errorMsg;

    @Schema(description = "操作时间")
    private String operateTime;

     // `       id`              bigint       not null auto_increment comment 'ID',
     //        `module_name`     varchar(64)  not null comment '模块名称',
     //        `business_type`   int          not null comment '业务类型（0-其它 1-新增 2-修改 3-删除）',
     //        `request_method`  varchar(10)  not null comment '请求方式',
     //        `operate_type`    int          not null comment '操作类型（0-其它 1-后台用户 2-手机端用户）',
     //        `operate_id`      bigint       not null comment '操作人员 ID',
     //        `operate_name`    varchar(64)  not null comment '操作人员名称',
     //        `request_url`     varchar(255) not null comment '请求url',
     //        `request_ip`      varchar(32)  not null comment '主机地址',
     //        `request_param`   json                  default '{}' comment '请求参数',
     //        `response_result` json                  default '{}' comment '响应结果',
     //        `operate_status`  int          not null comment '操作状态（0正常，1异常）',
     //        `error_msg`       json                  default '{}' comment '错误消息',
     //        `operate_time`    timestamp             default CURRENT_TIMESTAMP comment '操作时间',


}
