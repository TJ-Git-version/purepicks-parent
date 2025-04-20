package com.devsurfer.purepicks.user.controller;

import com.devsurfer.purepicks.model.dto.user.UserLoginDto;
import com.devsurfer.purepicks.model.dto.user.UserRegisterDto;
import com.devsurfer.purepicks.model.entity.user.UserInfo;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.user.service.UserService;
import com.devsurfer.purepicks.utils.IpAddressUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 21:13
 * description TODO
 */
@Tag(name = "会员用户管理")
@RestController
@RequestMapping("/api/user/userInfo")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "会员注册", description = "会员注册接口")
    public ResultUtil<?> register(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "会员登录", description = "会员登录: 使用账号密码登录")
    @PostMapping("/login")
    public ResultUtil<String> loginAccountPassword(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        String ipAddress = IpAddressUtil.getIpAddress(request);
        return ResultUtil.ok(userService.loginAccountPassword(ipAddress, userLoginDto));
    }

    @Operation(summary = "获取会员登录信息", description = "会员登录: 获取会员登录信息")
    @GetMapping("/auth/getCurrentUserInfo")
    public ResultUtil<UserInfo> getCurrentUserInfo(HttpServletRequest request) {
        return ResultUtil.ok(userService.getCurrentUserInfo(request));
    }

    @Operation(summary = "判断用户是否收藏该商品", description = "判断用户是否收藏该商品")
    @GetMapping("/isCollect/{skuId}")
    public ResultUtil<Boolean> isCollect(@PathVariable Long skuId) {
        return ResultUtil.ok(userService.isCollect(skuId));
    }

}
