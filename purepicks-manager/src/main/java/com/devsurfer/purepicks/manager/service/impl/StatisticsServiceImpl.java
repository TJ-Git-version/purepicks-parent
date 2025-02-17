package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.devsurfer.purepicks.manager.mapper.StatisticsMapper;
import com.devsurfer.purepicks.manager.service.StatisticsService;
import com.devsurfer.purepicks.model.dto.order.OrderStatisticsDto;
import com.devsurfer.purepicks.model.entity.order.OrderStatistics;
import com.devsurfer.purepicks.model.vo.order.OrderStatisticsVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsMapper statisticsMapper;

    public StatisticsServiceImpl(StatisticsMapper statisticsMapper) {
        this.statisticsMapper = statisticsMapper;
    }


    @Override
    public OrderStatisticsVo getOrderStatistics(OrderStatisticsDto orderStatisticsDto) {
        List<OrderStatistics> orderStatisticsList = statisticsMapper.findOrderStatisticsList(orderStatisticsDto);
        if (orderStatisticsList == null || orderStatisticsList.isEmpty()) {
            return new OrderStatisticsVo();
        }
        List<String> dateList = orderStatisticsList.stream().map(m -> DateUtil.formatDate(m.getOrderDate())).toList();
        List<BigDecimal> totalAmountList = orderStatisticsList.stream().map(OrderStatistics::getTotalAmount).toList();
        BigDecimal totalAmount = orderStatisticsList.stream().map(OrderStatistics::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new OrderStatisticsVo(dateList, totalAmountList, totalAmount);
    }
}
