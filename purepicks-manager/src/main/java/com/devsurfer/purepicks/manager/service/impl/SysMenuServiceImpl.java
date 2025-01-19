package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.devsurfer.purepicks.manager.helper.MenuHelper;
import com.devsurfer.purepicks.manager.mapper.SysMenuMapper;
import com.devsurfer.purepicks.manager.mapper.SysRoleMenuMapper;
import com.devsurfer.purepicks.manager.service.SysMenuService;
import com.devsurfer.purepicks.model.dto.system.menu.*;
import com.devsurfer.purepicks.model.entity.system.SysMenu;
import com.devsurfer.purepicks.model.entity.system.SysRoleMenu;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.menu.SysMenuVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 21:42
 * description TODO
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenuVo> findMenuList() {
        List<SysMenu> sysMenuList = sysMenuMapper.findMenuList();
        if (CollectionUtil.isEmpty(sysMenuList)) return null;
        List<SysMenuVo> sysMenuVoList = BeanUtil.copyToList(sysMenuList, SysMenuVo.class);
        return MenuHelper.buildTree(sysMenuVoList);
    }

    @Override
    public void addMenu(SysMenuInsertDto sysMenuInsertDto) {
        SysMenu sysMenuTitle = sysMenuMapper.selectOne(
                SysMenuQueryDto.builder()
                        .parentId(sysMenuInsertDto.getParentId())
                        .title(sysMenuInsertDto.getTitle())
                        .build()
        );
        if (sysMenuTitle != null) {
            PurePicksException.error(ResultCodeEnum.MENU_NAME_EXISTS_ERROR);
        }
        SysMenu sysMenuCode = sysMenuMapper.selectOne(
                SysMenuQueryDto.builder()
                        .parentId(sysMenuInsertDto.getParentId())
                        .title(sysMenuInsertDto.getMenuCode())
                        .build()
        );
        if (sysMenuCode != null) {
            PurePicksException.error(ResultCodeEnum.MENU_NAME_EXISTS_ERROR);
        }
        if (sysMenuInsertDto.getParentId() != 0) {
            sysMenuMapper.updateHasChildrenByParentId(sysMenuInsertDto.getParentId());
        }
        sysMenuMapper.insertMenu(BeanUtil.copyProperties(sysMenuInsertDto, SysMenu.class));
    }

    @Override
    public void editMenu(SysMenuUpdateDto sysMenuUpdateDto) {
        SysMenu sysMenuTitle = sysMenuMapper.selectOne(
                SysMenuQueryDto.builder()
                        .notId(sysMenuUpdateDto.getId())
                        .parentId(sysMenuUpdateDto.getParentId())
                        .title(sysMenuUpdateDto.getTitle())
                        .build()
        );
        if (sysMenuTitle != null) {
            PurePicksException.error(ResultCodeEnum.MENU_NAME_EXISTS_ERROR);
        }
        SysMenu sysMenuCode = sysMenuMapper.selectOne(
                SysMenuQueryDto.builder()
                        .notId(sysMenuUpdateDto.getId())
                        .parentId(sysMenuUpdateDto.getParentId())
                        .title(sysMenuUpdateDto.getMenuCode())
                        .build()
        );
        if (sysMenuCode != null) {
            PurePicksException.error(ResultCodeEnum.MENU_NAME_EXISTS_ERROR);
        }
        if (sysMenuUpdateDto.getParentId() != 0) {
            sysMenuMapper.updateHasChildrenByParentId(sysMenuUpdateDto.getParentId());
        }
        sysMenuMapper.updateMenu(BeanUtil.copyProperties(sysMenuUpdateDto, SysMenu.class));
    }

    @Override
    public void removeMenu(SysMenuDeleteDto sysMenuDeleteDto) {
        // 校验是否有子菜单，有则删除不成功
        Long sonCount = sysMenuMapper.countByParentId(sysMenuDeleteDto.getMenuId());
        if (sonCount > 0) {
            PurePicksException.error(ResultCodeEnum.MENU_DELETE_ERROR);
        }
        sysMenuMapper.removeMenu(sysMenuDeleteDto.getMenuId());
    }

    @Override
    public void assignMenu(AssignMenuDto assignMenuDto) {
        // 删除旧角色菜单
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());
        // 重新绑定新角色菜单
        sysRoleMenuMapper.insertBatch(assignMenuDto.getRoleId(), assignMenuDto.getMenuIdList());
    }

    @Override
    public Map<String, Object> findRoleMenuByRoleId(Long roleId) {
        List<SysMenuVo> menuTreeList = findMenuList();
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.findRoleMenuByRoleId(roleId);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("menuTreeList", menuTreeList);
        resultMap.put("roleMenuList", sysRoleMenuList);
        return resultMap;
    }
}
