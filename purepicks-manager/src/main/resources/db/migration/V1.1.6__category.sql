drop table if exists `category`;
create table `category`
(
    `id`          bigint auto_increment comment '主键ID',
    `parent_id`   bigint      not null comment '父级ID',
    `name`        varchar(32) not null comment '分类名称',
    `image_icon`  varchar(128) default '' comment '图标',
    `sort`        int          default 0 comment '排序',
    `status`      tinyint      default 1 comment '状态(0 不可见 | 1 可见)',
    `create_time` datetime     default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` datetime     default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`  tinyint      default 1 comment '逻辑删除(0 已删除 | 1 未删除)',
    primary key (`id`),
    index idx_category_parent_id (`parent_id`)
) comment '分类管理表';
