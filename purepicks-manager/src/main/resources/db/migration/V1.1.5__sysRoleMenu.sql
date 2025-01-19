drop table if exists `sys_role_menu`;
create table `sys_role_menu`
(
    `id`          bigint auto_increment comment '主键ID',
    `role_id`     bigint not null comment '角色id',
    `menu_id`     bigint not null comment '菜单id',
    primary key (`id`),
    index idx_role_menu_role_id (`role_id`),
    index idx_role_menu_menu_id (`menu_id`)
) comment '系统角色菜单关联表';
