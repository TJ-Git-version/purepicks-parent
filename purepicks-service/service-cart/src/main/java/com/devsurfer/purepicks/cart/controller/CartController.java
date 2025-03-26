package com.devsurfer.purepicks.cart.controller;

import com.devsurfer.purepicks.cart.service.CartService;
import com.devsurfer.purepicks.model.entity.h5.CartInfo;
import com.devsurfer.purepicks.model.result.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/26 21:03
 * description 购物车接口管理
 */
@Tag(name = "购物车接口管理")
@RestController
@RequestMapping("/api/order/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(summary = "添加购物车")
    @GetMapping("/auth/addToCart/{skuId}/{skuNum}")
    public ResultUtil<?> addToCart(
            @Parameter(name = "skuId", description = "商品skuId")
            @PathVariable("skuId") Long skuId,
            @Parameter(name = "skuId", description = "商品数量")
            @PathVariable("skuNum") Integer skuNum
    ) {
        cartService.addToCart(skuId, skuNum);
        return ResultUtil.ok();
    }

    @Operation(summary = "查询购物车列表")
    @GetMapping("/auth/cartList")
    public ResultUtil<List<CartInfo>> cartList() {
        return ResultUtil.ok(cartService.cartList());
    }

    @Operation(summary = "移除购物车选项")
    @GetMapping("/auth/deleteCart/{skuId}")
    public ResultUtil<?> deleteCart(
            @Parameter(name = "skuId", description = "商品skuId")
            @PathVariable("skuId") Long skuId
    ) {
        cartService.deleteCart(skuId);
        return ResultUtil.ok();
    }

    @Operation(summary = "取消指定购物车选中")
    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public ResultUtil<?> checkCart(
            @Parameter(name = "skuId", description = "商品skuId")
            @PathVariable("skuId") Long skuId,
            @Parameter(name = "isChecked", description = "是否选中: 1 选中 | 0 取消选中")
            @PathVariable("isChecked") Integer isChecked
    ) {
        cartService.checkCart(skuId, isChecked);
        return ResultUtil.ok();
    }

    @Operation(summary = "一键清空购物车选中")
    @GetMapping("/auth/clearCart")
    public ResultUtil<?> clearCart() {
        cartService.clearCart();
        return ResultUtil.ok();
    }

    @Operation(summary = "购物车商品的全选")
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public ResultUtil<?> allCheckCart(
            @Parameter(name = "isChecked", description = "全选: 1 | 不选中: 0")
            @PathVariable("isChecked") Integer isChecked
    ) {
        cartService.allCheckCart(isChecked);
        return ResultUtil.ok();
    }

}
