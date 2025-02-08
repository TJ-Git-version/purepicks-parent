drop table if exists `brand`;
create table `brand`
(
    `id`          bigint auto_increment,
    `name`        varchar(64) not null comment '品牌名称',
    `logo`        varchar(255)         default '' comment '品牌图标',
    `sort`        int                  default 1 comment '排序',
    `create_time` timestamp   not null default current_timestamp comment '创建时间',
    `update_time` timestamp   not null default current_timestamp on update current_timestamp comment '修改时间',
    `is_deleted`  tinyint(1)  not null default 1 comment '逻辑删除标记（0:不可用 1:可用）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='品牌信息表';