package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.role.*;
import com.devsurfer.purepicks.model.vo.system.role.SysRoleVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:51
 * description TODO
 */
public interface SysRoleService {

    /**
     * 分页查询角色列表
     */
    PageInfo<SysRoleVo> pageFindRoleList(SysRoleQueryDto sysRoleQueryDto);

    /**
     * 添加角色
     */
    void addRole(SysRoleInsertDto sysRoleInsertDto);

    /**
     * 编辑角色
     */
    void editRole(SysRoleUpdateDto sysRoleUpdateDto);

    /**
     * 删除角色
     */
    void removeRole(SysRoleDeleteDto sysRoleDeleteDto);

    /**
     * 分配角色
     */
    void assignRole(AssignRoleDto assignRoleDto);

    Map<String, Object> findRoleList(Long userId);
}
