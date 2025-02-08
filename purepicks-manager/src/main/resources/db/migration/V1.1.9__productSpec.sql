drop table if exists `product_spec`;
create table `product_spec`
(
    `id`          bigint auto_increment comment '主键ID',
    `spec_name`   varchar(64) not null comment '规格名称',
    `spec_value`  json        not null comment '规格值："[{"key":"颜色","valueList":["蓝","白","红"]]"',
    `create_time` timestamp  default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` timestamp  default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`  tinyint(1) default 1 comment '逻辑删除: 1 未删除, 0 已删除',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='商品规格表';