drop table if exists `sys_menu`;
create table `sys_menu`
(
    `id`           bigint      not null auto_increment comment '主键ID',
    `parent_id`    bigint               default 0 comment '父级ID',
    `title`        varchar(64) not null comment '菜单标题',
    `menu_code`    varchar(32)          default '' comment '路由编号',
    `sort`         int         not null default 1 comment '排序',
    `status`       tinyint     not null default 1 comment '菜单状态：0 禁用 | 1 正常',
    `has_children` tinyint     not null default 0 comment '是否有子节点：0 无 | 1 有',
    `create_time`  datetime    not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`  datetime    not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`   tinyint     not null default 1 comment '删除标记（0:不可用 1:可用）',
    primary key (`id`),
    index idx_parent_id (`parent_id`)
) comment '系统菜单';
