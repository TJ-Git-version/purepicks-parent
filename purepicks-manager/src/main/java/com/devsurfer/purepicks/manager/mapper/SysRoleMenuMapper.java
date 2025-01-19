package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.entity.system.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 20:56
 * description TODO
 */
public interface SysRoleMenuMapper {
    void deleteByRoleId(Long roleId);

    void insertBatch(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);

    List<SysRoleMenu> findRoleMenuByRoleId(Long roleId);

}
