# create database purepicks_manager charset utf8mb4;
# create user 'purepicks_manager_user'@'localhost' identified by 'devSurfer';
# grant all privileges on purepicks_manager.* to 'purepicks_manager_user'@'localhost';

drop table if exists `setting`;
create table `setting`
(
    `name`    varchar(64) not null comment '名称',
    `content` text        not null comment '内容'
) comment '配置表';

drop table if exists `sys_user`;
create table `sys_user`
(
    `id`          bigint auto_increment comment '主键ID',
    `username`    varchar(32) not null comment '用户名',
    `nickname`    varchar(64) not null comment '昵称',
    `password`    varchar(32) not null comment '密码',
    `phone`       varchar(11)  default '' comment '手机号',
    `avatar`      varchar(128) default '' comment '头像',
    `desc`        varchar(500) default '' comment '个人简介',
    `status`      tinyint      default 1 comment '用户状态: 0 禁用, 1 正常',
    `create_time` datetime     default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime     default CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`  tinyint      default 1 comment '逻辑删除: 1 未删除, 0 已删除',
    primary key (`id`),
    unique key (`username`)
) comment '系统用户表';
