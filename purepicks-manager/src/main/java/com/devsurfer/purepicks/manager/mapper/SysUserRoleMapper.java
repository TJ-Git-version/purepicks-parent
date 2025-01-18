package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.entity.system.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 16:22
 * description TODO
 */
public interface SysUserRoleMapper {

    void deleteUserRoleByUserId(Long userId);

    void insertBatch(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);

    boolean checkRoleIsUsed(@Param("roleIds") List<Long> roleIds);

    List<SysUserRole> findUserRoleByUserId(Long userId);

}
