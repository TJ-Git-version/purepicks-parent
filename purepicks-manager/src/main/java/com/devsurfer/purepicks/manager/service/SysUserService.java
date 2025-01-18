package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.user.SysUserDeleteDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserInsertDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserQueryDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserUpdateDto;
import com.devsurfer.purepicks.model.vo.system.user.SysUserVo;
import com.github.pagehelper.PageInfo;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 20:35
 */
public interface SysUserService {
    /**
     * 分页查询用户列表
     */
    PageInfo<SysUserVo> pageFindUserList(SysUserQueryDto sysUseQueryDto);

    /**
     * 添加用户
     */
    void addUser(SysUserInsertDto sysUserInsertDto);

    /**
     * 编辑用户
     */
    void editUser(SysUserUpdateDto sysUserUpdateDto);

    /**
     * 删除用户
     */
    void removeUser(SysUserDeleteDto sysUserDeleteDto);

    /**
     * 更新用户状态: 0 冻结用户 | 1 用户解冻
     */
    void updateUserStatus(Long userId, Integer status);

}
