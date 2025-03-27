drop table if exists `user_address`;
create table `user_address`
(
    `id`            bigint auto_increment comment '用户地址ID',
    `userId`        bigint       not null comment '用户ID',
    `username`      varchar(64)  not null comment '用户名称',
    `phone`         varchar(32)  not null comment '手机号',
    `tag_name`       varchar(64)           default '' comment '地址标签',
    `province_code` varchar(128) not null comment '省份编号',
    `city_code`     varchar(128) not null comment '市区编号',
    `district_code` varchar(128) not null comment '县区编号',
    `address`       mediumtext   not null comment '门牌号地址',
    `full_address`  mediumtext   not null comment '完整收货地址',
    `is_default`    tinyint      not null default 1 comment '是否为默认地址: 0 默认地址 | 1 普通地址',
    `create_time`   timestamp    not null default current_timestamp comment '创建时间',
    `update_time`   timestamp    not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted`    tinyint      not null default 1 comment '删除标记（0:不可用 1:可用）',
    primary key (`id`),
    index idx_user_id (`userId`)
) comment '用户地址表';
