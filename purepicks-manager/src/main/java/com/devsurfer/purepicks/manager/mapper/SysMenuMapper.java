package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.system.menu.SysMenuQueryDto;
import com.devsurfer.purepicks.model.entity.system.SysMenu;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 21:46
 * description 系统菜单mapper
 */
public interface SysMenuMapper {
    SysMenu selectOne(SysMenuQueryDto sysMenuQueryDto);

    void insertMenu(SysMenu sysMenu);

    void updateMenu(SysMenu sysMenu);

    void removeMenu(Long menuId);

    void updateHasChildrenByParentId(Long parentId);

    List<SysMenu> findMenuList();

    Long countByParentId(Long menuId);
}
