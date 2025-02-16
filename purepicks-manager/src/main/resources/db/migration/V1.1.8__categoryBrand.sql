drop table if exists `category_brand`;
create table `category_brand`
(
    `id`          bigint     not null auto_increment comment '主键ID',
    `category_id` bigint     not null comment '分类id',
    `brand_id`    bigint     not null comment '品牌id',
    `create_time` timestamp  not null default current_timestamp comment '更新时间',
    `update_time` timestamp  not null default current_timestamp on update current_timestamp comment '更新时间',
    `is_deleted`  tinyint(1) not null default 1 comment '逻辑删除（0:不可用 1:可用）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='分类品牌表';
