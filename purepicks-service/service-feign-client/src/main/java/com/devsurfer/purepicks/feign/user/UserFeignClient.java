package com.devsurfer.purepicks.feign.user;

import com.devsurfer.purepicks.model.entity.user.UserAddress;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/31 23:10
 * description 用户远程调用接口
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {

    @Operation(summary = "根据地址id获取地址信息")
    @GetMapping("/api/user/userAddress/getUserAddress/{userAddressId}")
    UserAddress getUserAddress(@PathVariable(value = "userAddressId") Long userAddressId);

}
