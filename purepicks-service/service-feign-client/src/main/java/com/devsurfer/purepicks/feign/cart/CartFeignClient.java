package com.devsurfer.purepicks.feign.cart;

import com.devsurfer.purepicks.model.entity.h5.CartInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/27 22:22
 * description TODO
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {

    /**
     * 获取所有选中购物车列表
     */
    @GetMapping("/api/order/cart/auth/getAllCheckedList")
    List<CartInfo> getAllCheckedCartList();

    /**
     * 清空选中的购物车
     */
    @GetMapping("/api/order/cart/auth/deleteChecked")
    void deleteChecked();


}
