package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.SysRoleService;
import com.devsurfer.purepicks.model.dto.system.role.*;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.system.role.SysRoleVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:27
 * description 系统角色管理
 */
@Tag(name = "系统角色管理")
@RestController
@RequestMapping("/admin/system/role")
@AllArgsConstructor
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @Operation(summary = "分页查询角色列表", description = "分页查询角色列表")
    @GetMapping("/list")
    public ResultUtil<PageInfo<SysRoleVo>> pageFindRoleList(SysRoleQueryDto sysRoleQueryDto) {
        return ResultUtil.ok(sysRoleService.pageFindRoleList(sysRoleQueryDto));
    }

    @Operation(summary = "新增角色", description = "新增角色")
    @PostMapping
    public ResultUtil<?> addRole(@RequestBody SysRoleInsertDto sysRoleInsertDto) {
        sysRoleService.addRole(sysRoleInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "修改角色", description = "修改角色")
    @PutMapping
    public ResultUtil<?> editRole(@RequestBody SysRoleUpdateDto sysRoleUpdateDto) {
        sysRoleService.editRole(sysRoleUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "删除角色", description = "删除角色")
    @DeleteMapping
    public ResultUtil<?> removeRole(@RequestBody SysRoleDeleteDto sysRoleDeleteDto) {
        sysRoleService.removeRole(sysRoleDeleteDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "分配用户角色", description = "分配用户角色")
    @PostMapping("/assign-role")
    public ResultUtil<?> assignRole(@RequestBody AssignRoleDto assignRoleDto) {
        sysRoleService.assignRole(assignRoleDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "查询所有角色", description = "查询所有角色(如果传递userId,则会查询当前用户之前绑定的角色)")
    @PostMapping("/find-role-list")
    public ResultUtil<Map<String, Object>> findRoleList(@RequestParam(value = "userId", required = false) Long userId) {
        return ResultUtil.ok(sysRoleService.findRoleList(userId));
    }

}
