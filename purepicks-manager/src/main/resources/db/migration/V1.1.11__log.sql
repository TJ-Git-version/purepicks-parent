drop table if exists `operation_log`;
create table `operation_log`
(
    `id`              bigint       not null auto_increment comment 'ID',
    `module_name`     varchar(64)  not null comment '模块名称',
    `business_type`   int          not null comment '业务类型（0-其它, 1-新增, 2-修改, 3-删除）',
    `request_method`  varchar(10)  not null comment '请求方式',
    `operate_type`    int          not null comment '操作类型（0-其它, 1-后台用户, 2-手机端用户）',
    `operate_id`      bigint       not null comment '操作人员 ID',
    `operate_name`    varchar(64)  not null comment '操作人员名称',
    `request_url`     varchar(255) not null comment '请求url',
    `request_ip`      varchar(32)  not null comment '主机地址',
    `request_param`   json comment '请求参数',
    `response_result` json comment '响应结果',
    `operate_status`  int          not null comment '操作状态（0正常, 1异常）',
    `error_msg`       json comment '错误消息',
    `create_time`     timestamp             default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`     timestamp             default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`      tinyint      not null default 1 comment '逻辑删除：1 可用，0 不可用',
    primary key (`id`),
    index idx_operate_name (`operate_name`),
    index idx_operate_id (`operate_id`)
) comment '操作日志表';

