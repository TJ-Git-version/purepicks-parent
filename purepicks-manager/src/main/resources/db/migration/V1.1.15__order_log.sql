drop table if exists `order_log`;
create table `order_log`
(
    `id`             bigint auto_increment comment '订单日志ID',
    `order_id`       bigint     not null comment '订单ID',
    `operate_user`   tinyint    not null comment '操作用户: 0 其他; 1 后端用户; 2 手机端用户',
    `process_status` int        not null comment '订单状态:  -1 已取消; 0 待付款; 1 待发货; 2 已发货; 3 待用户收获, 订单已完成;',
    `note`           mediumtext null comment '备注',
    `create_time`    timestamp  not null default current_timestamp comment '创建时间',
    `update_time`    timestamp  not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted`     tinyint    not null default 1 comment '删除标记（0:不可用 1:可用）',
    primary key (`id`),
    index idx_order_id (`order_id`)
) comment '用户地址表';
