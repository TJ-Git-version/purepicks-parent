package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.order.OrderStatisticsDto;
import com.devsurfer.purepicks.model.vo.order.OrderStatisticsVo;

public interface StatisticsService {

    OrderStatisticsVo getOrderStatistics(OrderStatisticsDto orderStatisticsDto);

}
