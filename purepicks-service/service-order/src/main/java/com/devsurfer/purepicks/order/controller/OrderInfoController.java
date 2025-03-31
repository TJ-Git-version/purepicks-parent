package com.devsurfer.purepicks.order.controller;

import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.h5.TradeVo;
import com.devsurfer.purepicks.order.service.OrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/30 22:39
 * description 订单管理
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping("/api/order/orderInfo")
@AllArgsConstructor
public class OrderInfoController {

    private final OrderInfoService orderInfoService;

    @Operation(summary = "确认下单,获取选中购物车列表")
    @GetMapping("/auth/trade")
    private ResultUtil<TradeVo> getOrderTradeVo() {
        return ResultUtil.ok(orderInfoService.getOrderTradeVo());
    }

}
