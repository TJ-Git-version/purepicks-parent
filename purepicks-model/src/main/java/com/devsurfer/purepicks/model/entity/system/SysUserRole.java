package com.devsurfer.purepicks.model.entity.system;

import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 16:46
 * description 用户-角色-关联表
 */
@Data
public class SysUserRole {

    // 主键id
    private Long id;

    // 用户id
    private Long userId;

    // 角色id
    private Long roleId;

}
