package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.SysMenuService;
import com.devsurfer.purepicks.model.dto.system.menu.AssignMenuDto;
import com.devsurfer.purepicks.model.dto.system.menu.SysMenuDeleteDto;
import com.devsurfer.purepicks.model.dto.system.menu.SysMenuInsertDto;
import com.devsurfer.purepicks.model.dto.system.menu.SysMenuUpdateDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.system.menu.SysMenuVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 21:20
 * description 系统菜单
 */
@Tag(name = "系统菜单管理")
@RestController
@RequestMapping("/admin/system/menu")
@AllArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @Operation(summary = "查询菜单列表", description = "查询菜单列表")
    @GetMapping("/list")
    public ResultUtil<List<SysMenuVo>> findMenuList() {
        return ResultUtil.ok(sysMenuService.findMenuList());
    }

    @Operation(summary = "新增菜单", description = "新增菜单")
    @PostMapping
    public ResultUtil<?> addMenu(@RequestBody SysMenuInsertDto sysMenuInsertDto) {
        sysMenuService.addMenu(sysMenuInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "修改菜单", description = "修改菜单")
    @PutMapping
    public ResultUtil<?> editMenu(@RequestBody SysMenuUpdateDto sysMenuUpdateDto) {
        sysMenuService.editMenu(sysMenuUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @DeleteMapping
    public ResultUtil<?> removeMenu(@RequestBody SysMenuDeleteDto sysMenuDeleteDto) {
        sysMenuService.removeMenu(sysMenuDeleteDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "分配角色菜单", description = "分配角色菜单")
    @PostMapping("/assign-menu")
    public ResultUtil<?> assignMenu(@RequestBody AssignMenuDto assignMenuDto) {
        sysMenuService.assignMenu(assignMenuDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "查询角色绑定菜单列表", description = "查询角色绑定菜单列表")
    @GetMapping("/find-role-menu-by-roleid")
    public ResultUtil<Map<String, Object>> findRoleMenuByRoleId(@RequestParam("roleId") Long roleId) {
        return ResultUtil.ok(sysMenuService.findRoleMenuByRoleId(roleId));
    }

}
