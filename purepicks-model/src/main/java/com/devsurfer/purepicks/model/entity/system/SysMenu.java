package com.devsurfer.purepicks.model.entity.system;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 21:24
 * description 系统菜单实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenu extends BaseEntity {

    // 父级ID
    private Long parentId;

    // 菜单标题
    private String title;

    // 菜单编号
    private String menuCode;

    // 排序
    private Integer sort;

    // 状态
    private Integer status;

    // 是否有字节的(0 无 | 1 有)
    private Integer hasChildren;

}
