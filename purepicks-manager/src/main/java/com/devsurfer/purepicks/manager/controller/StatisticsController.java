package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.StatisticsService;
import com.devsurfer.purepicks.model.dto.order.OrderStatisticsDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.order.OrderStatisticsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "统计管理", description = "统计管理相关接口")
@RestController
@RequestMapping("/admin/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "获取订单统计信息", description = "获取订单统计信息")
    @GetMapping("/order")
    public ResultUtil<OrderStatisticsVo> getOrderStatistics(OrderStatisticsDto orderStatisticsDto) {
        return ResultUtil.ok(statisticsService.getOrderStatistics(orderStatisticsDto));
    }

}
