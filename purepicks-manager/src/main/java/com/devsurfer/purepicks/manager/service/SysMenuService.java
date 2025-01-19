package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.menu.AssignMenuDto;
import com.devsurfer.purepicks.model.dto.system.menu.SysMenuDeleteDto;
import com.devsurfer.purepicks.model.dto.system.menu.SysMenuInsertDto;
import com.devsurfer.purepicks.model.dto.system.menu.SysMenuUpdateDto;
import com.devsurfer.purepicks.model.vo.system.menu.SysMenuVo;

import java.util.List;
import java.util.Map;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 21:42
 * description 系统菜单接口实现
 */
public interface SysMenuService {
    List<SysMenuVo> findMenuList();

    void addMenu(SysMenuInsertDto sysMenuInsertDto);

    void editMenu(SysMenuUpdateDto sysMenuUpdateDto);

    void removeMenu(SysMenuDeleteDto sysMenuDeleteDto);

    void assignMenu(AssignMenuDto assignMenuDto);

    Map<String, Object> findRoleMenuByRoleId(Long roleId);
}
