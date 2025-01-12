drop table if exists `sys_role`;
create table `sys_role`
(
    `id`          bigint auto_increment comment '主键ID',
    `role_name`   varchar(32)  not null comment '角色名称',
    `role_code`   varchar(32)  not null comment '角色编码',
    `desc`        varchar(500) not null comment '角色简介',
    `create_time` datetime default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`  tinyint  default 1 comment '逻辑删除: 1 未删除, 0 已删除',
    primary key (`id`)
) comment '系统角色表';

drop table if exists `sys_user_role`;
create table `sys_user_role`
(
    `id`          bigint auto_increment comment '主键ID',
    `role_id`     bigint not null comment '角色id',
    `user_id`     bigint not null comment '用户id',
    primary key (`id`),
    index idx_user_role_role_id (`role_id`),
    index idx_user_role_user_id (`user_id`)
) comment '系统用户角色关联表';
