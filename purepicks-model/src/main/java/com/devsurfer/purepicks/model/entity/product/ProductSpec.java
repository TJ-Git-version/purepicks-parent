package com.devsurfer.purepicks.model.entity.product;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpec extends BaseEntity {

    // 商品规格ID
    private Long id;

    // 商品规格名称
    private String specName;

    // 商品规格值
    private String specValue; // JSON类型在Java中通常用String表示，除非使用特定的JSON库

}