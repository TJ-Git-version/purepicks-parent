package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.entity.system.SysUser;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:13
 * description TODO
 */
public interface SysUserMapper {

    SysUser selectByUserName(String username);

}
