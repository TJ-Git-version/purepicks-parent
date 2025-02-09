package com.devsurfer.purepicks.model.entity.category;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:33
 * description 分类管理实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends BaseEntity {

    // 父级ID
    private Long parentId;

    // 分类名称
    private String name;

    // 图标
    private String imageIcon;

    // 排序
    private Integer sort;

    // 状态(0 不可见 | 1 可见)
    private Integer status;

}
