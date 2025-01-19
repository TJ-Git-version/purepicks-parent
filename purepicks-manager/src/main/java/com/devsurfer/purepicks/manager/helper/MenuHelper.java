package com.devsurfer.purepicks.manager.helper;

import com.devsurfer.purepicks.model.vo.system.menu.SysMenuVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 20:04
 * description TODO
 */
public class MenuHelper {


    public static List<SysMenuVo> buildTree(List<SysMenuVo> sysMenuVoList) {
        List<SysMenuVo> menuTreeList = new ArrayList<>();
        for (SysMenuVo sysMenuVo : sysMenuVoList) {
            if (sysMenuVo.getParentId() == 0) {
                menuTreeList.add(findChildren(sysMenuVo, sysMenuVoList));
            }
        }
        //return menuTreeList.stream().sorted(Comparator.comparingInt(SysMenuVo::getSort)).collect(Collectors.toList());
        return menuTreeList;
    }

    private static SysMenuVo findChildren(SysMenuVo sysMenuVo, List<SysMenuVo> treeNodes) {
        if (sysMenuVo.getHasChildren() == 1) {
            for (SysMenuVo treeNode : treeNodes) {
                if (Objects.equals(sysMenuVo.getId(), treeNode.getParentId())) {
                    sysMenuVo.getChildrenMenu().add(findChildren(treeNode, treeNodes));
                }
            }
        }
        // 升序
        //sysMenuVo.setChildrenMenu(sysMenuVo.getChildrenMenu().stream().sorted(Comparator.comparingInt(SysMenuVo::getSort)).collect(Collectors.toList()));
        return sysMenuVo;
    }
}
