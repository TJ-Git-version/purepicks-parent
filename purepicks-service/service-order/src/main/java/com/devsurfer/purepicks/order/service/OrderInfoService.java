package com.devsurfer.purepicks.order.service;

import com.devsurfer.purepicks.model.dto.h5.OrderInfoDto;
import com.devsurfer.purepicks.model.vo.h5.TradeVo;
import com.devsurfer.purepicks.model.vo.order.OrderInfoVo;
import com.github.pagehelper.PageInfo;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/30 22:42
 * description TODO
 */
public interface OrderInfoService {

    TradeVo getOrderTradeVo();

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfoVo getOrderInfoById(Long orderId);

    TradeVo buy(Long skuId);

    PageInfo<OrderInfoVo> myOrders(Integer page, Integer limit, Integer orderStatus);
}
