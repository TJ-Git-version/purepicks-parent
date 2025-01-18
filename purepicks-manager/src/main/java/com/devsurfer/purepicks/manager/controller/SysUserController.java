package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.SysUserService;
import com.devsurfer.purepicks.model.dto.system.user.SysUserDeleteDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserInsertDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserQueryDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserUpdateDto;
import com.devsurfer.purepicks.model.enums.user.SysUserStatusEnums;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.system.user.SysUserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 20:20
 * description 系统用户管理
 */
@Tag(name = "系统用户管理")
@RestController
@RequestMapping("/admin/system/user")
@AllArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @Operation(summary = "分页查询用户列表", description = "分页查询用户列表")
    @GetMapping("/list")
    public ResultUtil<PageInfo<SysUserVo>> pageFindUserList(SysUserQueryDto sysUseQueryDto) {
        return ResultUtil.ok(sysUserService.pageFindUserList(sysUseQueryDto));
    }

    @Operation(summary = "新增用户", description = "新增用户")
    @PostMapping
    public ResultUtil<?> addUser(@RequestBody SysUserInsertDto sysUserInsertDto) {
        sysUserService.addUser(sysUserInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "修改用户", description = "修改用户")
    @PutMapping
    public ResultUtil<?> editUser(@RequestBody SysUserUpdateDto sysUserUpdateDto) {
        sysUserService.editUser(sysUserUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "删除用户", description = "删除用户")
    @DeleteMapping
    public ResultUtil<?> removeUser(@RequestBody SysUserDeleteDto sysUserDeleteDto) {
        sysUserService.removeUser(sysUserDeleteDto);
        return ResultUtil.ok();
    }


    @Operation(summary = "冻结用户", description = "冻结用户")
    @PutMapping("/frozen-user/{userId}")
    public ResultUtil<?> frozenUser(@PathVariable("userId") Long userId) {
        sysUserService.updateUserStatus(userId, SysUserStatusEnums.USER_BANNED.getStatus());
        return ResultUtil.ok();
    }

    @Operation(summary = "用户解冻", description = "用户解冻")
    @PutMapping("/user-unfreezing/{userId}")
    public ResultUtil<?> userUnfreezing(@PathVariable("userId") Long userId) {
        sysUserService.updateUserStatus(userId, SysUserStatusEnums.USER_NORMAL.getStatus());
        return ResultUtil.ok();
    }

}
