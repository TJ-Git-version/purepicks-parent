package com.devsurfer.purepicks.order.service.impl;

import com.devsurfer.purepicks.feign.cart.CartFeignClient;
import com.devsurfer.purepicks.model.entity.h5.CartInfo;
import com.devsurfer.purepicks.model.entity.order.OrderItem;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.h5.TradeVo;
import com.devsurfer.purepicks.order.service.OrderInfoService;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/30 22:42
 * description TODO
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    private final CartFeignClient cartFeignClient;

    public OrderInfoServiceImpl(CartFeignClient cartFeignClient) {
        this.cartFeignClient = cartFeignClient;
    }

    @Override
    public TradeVo getOrderTradeVo() {
        List<CartInfo> checkedCartList = cartFeignClient.getAllCheckedCartList();
        if (checkedCartList == null) {
            PurePicksException.error(ResultCodeEnum.CART_EMPTY_ERROR);
        }
        List<OrderItem> orderItemList = checkedCartList.stream().map(cartInfo -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setCreateTime(cartInfo.getCreateTime());
            orderItem.setUpdateTime(cartInfo.getUpdateTime());
            return orderItem;
        }).toList();
        BigDecimal totalPrice = orderItemList.stream().map(OrderItem::computerPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new TradeVo(totalPrice, orderItemList);
    }
}
