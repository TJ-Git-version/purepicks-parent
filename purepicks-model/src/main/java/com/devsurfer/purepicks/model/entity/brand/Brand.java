package com.devsurfer.purepicks.model.entity.brand;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {

    // 主键
    private Long id;

    // 品牌名称
    private String name;

    // 品牌logo
    private String logo;

    // 品牌排序
    private Integer sort;

}
