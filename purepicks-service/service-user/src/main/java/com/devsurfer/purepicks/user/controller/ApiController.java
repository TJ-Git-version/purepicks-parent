package com.devsurfer.purepicks.user.controller;

import com.devsurfer.purepicks.feign.user.UserFeignClient;
import com.devsurfer.purepicks.model.entity.user.UserAddress;
import com.devsurfer.purepicks.user.mapper.UserAddressMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/31 23:14
 * description TODO
 */
@Tag(name = "用户feign远程调用实现类")
@RestController
@AllArgsConstructor
public class ApiController implements UserFeignClient {

    private final UserAddressMapper userAddressMapper;

    @Override
    @Operation(description = "获取用户地址信息")
    public UserAddress getUserAddress(Long userAddressId) {
        return userAddressMapper.getUserAddress(userAddressId);
    }
}
