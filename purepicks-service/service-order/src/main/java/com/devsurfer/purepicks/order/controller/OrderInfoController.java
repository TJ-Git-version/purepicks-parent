package com.devsurfer.purepicks.order.controller;

import com.devsurfer.purepicks.model.dto.h5.OrderInfoDto;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.h5.TradeVo;
import com.devsurfer.purepicks.model.vo.order.OrderInfoVo;
import com.devsurfer.purepicks.order.service.OrderInfoService;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/auth/trade")
    @Operation(summary = "确认下单,获取选中购物车列表")
    public ResultUtil<TradeVo> getOrderTradeVo() {
        return ResultUtil.ok(orderInfoService.getOrderTradeVo());
    }

    @PostMapping("/auth/submitOrder")
    @Operation(summary = "提交订单")
    public ResultUtil<Long> submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        if (orderInfoDto.getOrderItemList() == null || orderInfoDto.getOrderItemList().isEmpty() || orderInfoDto.getUserAddressId() == null) {
            PurePicksException.error(ResultCodeEnum.PARAMETER_INVALID_ERROR);
        }
        return ResultUtil.ok(orderInfoService.submitOrder(orderInfoDto));
    }

    @GetMapping("/auth/{orderId}")
    @Operation(summary = "根据订单id获取详情")
    public ResultUtil<OrderInfoVo> getOrderInfoById(@PathVariable Long orderId) {
        return ResultUtil.ok(orderInfoService.getOrderInfoById(orderId));
    }

    @GetMapping("/auth/buy/{skuId}")
    @Operation(summary = "立即购买返回详情")
    public ResultUtil<TradeVo> buy(@PathVariable Long skuId) {
        return ResultUtil.ok(orderInfoService.buy(skuId));
    }

    @GetMapping("/auth/{page}/{limit}")
    @Operation(summary = "立即购买返回详情")
    public ResultUtil<PageInfo<OrderInfoVo>> myOrders(@PathVariable Integer page,
                                         @PathVariable Integer limit,
                                         @RequestParam(value = "orderStatus", required = false) Integer orderStatus
    ) {
        return ResultUtil.ok(orderInfoService.myOrders(page, limit, orderStatus));
    }

}
