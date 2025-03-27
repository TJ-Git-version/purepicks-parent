package com.devsurfer.purepicks.cart.service;

import com.devsurfer.purepicks.model.entity.h5.CartInfo;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/26 21:07
 * description TODO
 */
public interface CartService {

    void addToCart(Long skuId, Integer skuNum);

    List<CartInfo> cartList();

    void deleteCart(Long skuId);

    void checkCart(Long skuId, Integer isChecked);

    void clearCart();

    void allCheckCart(Integer isChecked);

    List<CartInfo> getAllChecked();

}
