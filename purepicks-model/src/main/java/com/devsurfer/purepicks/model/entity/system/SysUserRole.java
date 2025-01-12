package com.devsurfer.purepicks.model.entity.system;

import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:32
 * description TODO
 */
@Data
public class SysUserRole {

    // 用户角色关联表ID
    private Long id;

    // 角色ID
    private Long roleId;

    // 用户ID
    private Long userId;

}
