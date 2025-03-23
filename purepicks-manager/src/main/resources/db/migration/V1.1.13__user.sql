drop table if exists `user_info`;
create table `user_info`
(
    `id`              bigint auto_increment comment '用户ID',
    `open_id`         varchar(256) not null default '' comment '微信登录唯一标识',
    `union_id`        varchar(256) not null default '' comment '微信开放平台unionID',
    `username`        varchar(64)  not null comment '用户名',
    `nickname`        varchar(64)  not null default '' comment '用户昵称',
    `password`        varchar(128) not null comment '密码',
    `avatar`          varchar(256) not null default '' comment '头像URL',
    `sex`             tinyint(1)   not null default 0 comment '性别: 0 男生, 1 女生',
    `phone`           varchar(64)  not null default '' comment '电话号码',
    `status`          tinyint(1)   not null default 1 comment '状态: 1 正常, 0 封号',
    `last_login_ip`   varchar(64)  not null default '' comment '最后一次登录ip',
    `last_login_time` timestamp    not null default current_timestamp on update current_timestamp comment '最后一次登录时间',
    `remark`          mediumtext comment '备注',
    `create_time`     timestamp    not null default current_timestamp comment '创建时间',
    `update_time`     timestamp    not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted`      tinyint      not null default 1 comment '删除标记（0:不可用 1:可用）',
    primary key (`id`)
) comment '用户表';
