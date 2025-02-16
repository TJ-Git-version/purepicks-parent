drop table if exists `product`;
create table `product`
(
    # 商品名称、品牌id、轮播图、一级分类、二级分类、三级分类、计量单位、状态、
    # 商品规格id（用于后续规格更新了可以做同步-可选字段）、商品规格信息（json）
    # 创建人、创建人id、创建时间、更新时间、审核人、审核人id、审核状态、审核审核时间、审核信息
    `id`                bigint auto_increment comment '主键ID',
    `product_name`      varchar(64) not null comment '商品名称',
    `brand_id`          bigint      not null comment '品牌id',
    `carousel_url`      text        not null comment '轮播图',
    `category_id1`      bigint      not null comment '一级分类',
    `category_id2`      bigint      not null comment '二级分类',
    `category_id3`      bigint      not null comment '三级分类',
    `unit_name`         varchar(32) not null comment '计量单位',
    `status`            tinyint     not null default 0 comment '商品状态：0-未审核，1-上架，2-下架',
    `product_spec_id`   bigint      not null comment '商品规格id',
    `product_spec_info` json        not null comment '商品规格信息',
    `create_user_id`    bigint      not null comment '创建人id',
    `review_user_id`    bigint      not null comment '审核人id',
    `review_time`       timestamp            default CURRENT_TIMESTAMP comment '审核时间',
    `review_status`     tinyint     not null default 0 comment '审核状态：0-未审核，1-通过，2-驳回',
    `review_info`       text comment '审核信息',
    `create_time`       timestamp            default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`       timestamp            default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`        tinyint(1)           default 1 comment '逻辑删除: 1 未删除, 0 已删除',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='商品管理表';

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

drop table if exists `product_unit`;
create table `product_unit`
(
    `id`          bigint auto_increment comment '主键ID',
    `name`        varchar(32) not null comment '计量单位名称',
    `create_time` timestamp  default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` timestamp  default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`  tinyint(1) default 1 comment '逻辑删除: 1 未删除, 0 已删除',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='商品计量单位表';

drop table if exists `product_detail`;
create table `product_detail`
(
    `id`          bigint auto_increment comment '主键ID',
    `product_id`  bigint not null comment '商品id',
    `image_url`   text   not null comment '商品详情图片',
    `create_time` timestamp  default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` timestamp  default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`  tinyint(1) default 1 comment '逻辑删除: 1 未删除, 0 已删除',
    primary key (`id`),
    index idx_pd_product_id (`product_id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='商品详情表';

drop table if exists `product_sku`;
create table `product_sku`
(
    `id`           bigint         not null auto_increment comment '主键ID',
    `product_id`   bigint         not null comment '商品id',
    `code`         varchar(32)    not null comment '商品SKU编码',
    `name`         varchar(64)    not null comment '商品SKU名称',
    `thumb_image`  text           not null comment '商品SKU图片',
    `sale_price`   decimal(12, 2) not null comment '售价',
    `market_price` decimal(12, 2) not null comment '市场价',
    `cost_price`   decimal(12, 2) not null comment '成本价',
    `spec_name`    varchar(128)   not null comment '规格名称',
    `stock_num`    int            not null comment '库存量',
    `weight`       double(10, 2)  not null comment '重量',
    `volume`       double(10, 2)  not null comment '体积',
    `status`       tinyint(1)     not null default 1 comment '状态: 0-下架, 1-上架',
    `create_time`  timestamp               default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`  timestamp               default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`   tinyint(1)              default 1 comment '逻辑删除: 1 未删除, 0 已删除',
    primary key (`id`),
    index idx_ps_product_id (`product_id`)
) engine = innodb
  default charset = utf8mb4
  collate = utf8mb4_unicode_ci comment ='商品SKU表';
