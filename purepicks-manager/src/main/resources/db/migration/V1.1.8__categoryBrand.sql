drop table if exists `category_brand`;
create table `category_brand`
(
    `categoryId`  bigint    not null comment '分类id',
    `brandId`     bigint    not null comment '品牌id',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`categoryId`, `brandId`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='分类品牌表';