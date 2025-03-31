package com.devsurfer.purepicks.cart.controller;

import com.devsurfer.purepicks.cart.service.CartService;
import com.devsurfer.purepicks.feign.cart.CartFeignClient;
import com.devsurfer.purepicks.model.entity.h5.CartInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/31 22:16
 * description 购物车feign调用实现
 */
@Tag(name = "购物车feign调用实现")
@RestController
@AllArgsConstructor
public class ApiController implements CartFeignClient {

    private final CartService cartService;

    @Operation(summary = "获取选中的购物车")
    @Override
    public List<CartInfo> getAllCheckedCartList() {
        return cartService.getAllChecked();
    }
}
