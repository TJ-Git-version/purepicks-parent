-- 订单信息表
drop table if exists `order_info`;
create table `order_info`
(
    `id`                    bigint         not null auto_increment comment 'id',
    `user_id`               bigint         not null default '0' comment '会员_id',
    `nick_name`             varchar(200)            default null comment '昵称',
    `order_no`              char(64)       not null default '' comment '订单号',
    `coupon_id`             bigint                  default null comment '使用的优惠券',
    `total_amount`          decimal(10, 2) not null default '0.00' comment '订单总额',
    `coupon_amount`         decimal(10, 2) not null default '0.00' comment '优惠券',
    `original_total_amount` decimal(10, 2) not null default '0.00' comment '原价金额',
    `freight_fee`           decimal(10, 2) not null default '0.00' comment '运费',
    `pay_type`              tinyint                 default null comment '支付方式【1->微信】',
    `order_status`          tinyint        not null default '0' comment '订单状态【0->待付款；1->待发货；2->已发货；3->待用户收货，已完成；-1->已取消】',
    `receiver_name`         varchar(100)            default null comment '收货人姓名',
    `receiver_phone`        varchar(32)             default null comment '收货人电话',
    `receiver_post_code`    varchar(32)             default null comment '收货人邮编',
    `receiver_province`     bigint                  default null comment '省份/直辖市',
    `receiver_city`         bigint                  default null comment '城市',
    `receiver_district`     bigint                  default null comment '区',
    `receiver_address`      varchar(200)            default null comment '详细地址',
    `payment_time`          datetime                default null comment '支付时间',
    `delivery_time`         datetime                default null comment '发货时间',
    `receive_time`          datetime                default null comment '确认收货时间',
    `remark`                varchar(500)            default null comment '订单备注',
    `cancel_time`           datetime                default null comment '取消订单时间',
    `cancel_reason`         varchar(255)            default null comment '取消订单原因',
    `create_time`           timestamp      not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`           timestamp      not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`            tinyint        not null default '1' comment '删除标记（0:不可用 1:可用）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb3 comment ='订单';

-- 订单明细表
drop table if exists `order_item`;
create table `order_item`
(
    `id`          bigint    not null auto_increment comment 'id',
    `order_id`    bigint             default null comment 'order_id',
    `sku_id`      bigint             default null comment '商品sku编号',
    `sku_name`    varchar(255)       default null comment '商品sku名字',
    `thumb_img`   varchar(500)       default null comment '商品sku图片',
    `sku_price`   decimal(10, 2)     default null comment '商品sku价格',
    `sku_num`     int                default null comment '商品购买的数量',
    `create_time` timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_time` timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`  tinyint   not null default '1' comment '删除标记（0:不可用 1:可用）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb3 comment ='订单项信息';

-- 订单统计表
drop table if exists `order_statistics`;
create table `order_statistics`
(
    `id`           bigint    not null auto_increment comment 'id',
    `order_date`   date               default null comment '订单统计日期',
    `total_amount` decimal(10, 2)     default null comment '总金额',
    `total_num`    int                default null comment '订单总数',
    `create_time`  timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    `update_time`  timestamp not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    `is_deleted`   tinyint   not null default '1' comment '删除标记（0:不可用 1:可用）',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb3 comment ='订单统计';