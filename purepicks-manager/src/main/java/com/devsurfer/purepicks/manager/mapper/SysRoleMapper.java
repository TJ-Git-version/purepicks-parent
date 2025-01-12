package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.system.role.SysRoleQueryDto;
import com.devsurfer.purepicks.model.entity.system.SysRole;
import com.devsurfer.purepicks.model.vo.system.role.SysRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:54
 * description TODO
 */
public interface SysRoleMapper {

    List<SysRoleVo> findRoleList(SysRoleQueryDto sysRoleQueryDto);

    SysRole selectByRoleName(@Param("roleName") String roleName);

    SysRole selectByRoleCode(@Param("roleCode") String roleCode);

    void insertRole(SysRole sysRole);

    boolean checkByIdAndRoleName(@Param("id") Long id, @Param("roleName") String roleName);

    boolean checkByIdAndRoleCode(@Param("id") Long id, @Param("roleCode") String roleCode);

    void editRole(SysRole sysRole);

    void deleteRoleInId(@Param("roleIds") List<Long> roleIds);
}
