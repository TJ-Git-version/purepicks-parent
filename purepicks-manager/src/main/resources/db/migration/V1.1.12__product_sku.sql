ALTER TABLE `purepicks_manager`.`product_sku`
    ADD COLUMN `sale_num` bigint NOT NULL DEFAULT 0 COMMENT '销售数量' AFTER `stock_num`;
