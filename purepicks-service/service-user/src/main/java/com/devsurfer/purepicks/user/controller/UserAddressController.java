package com.devsurfer.purepicks.user.controller;

import com.devsurfer.purepicks.model.entity.user.UserAddress;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.user.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/27 22:07
 * description TODO
 */
@Tag(name = "用户地址接口管理", description = "用户地址接口管理")
@RestController
@RequestMapping("/api/user/userAddress")
@AllArgsConstructor
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping("/auth/findUserAddressList")
    @Operation(summary = "获取用户地址列表")
    public ResultUtil<List<UserAddress>> findUserAddressList() {
        return ResultUtil.ok(userAddressService.findUserAddressList());
    }

}
