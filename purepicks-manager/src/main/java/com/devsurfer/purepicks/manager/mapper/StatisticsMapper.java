package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.order.OrderStatisticsDto;
import com.devsurfer.purepicks.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticsMapper {

    void insert(@Param("orderStatistics") OrderStatistics orderStatistics, @Param("yesterday") String yesterday);

    List<OrderStatistics> findOrderStatisticsList(OrderStatisticsDto orderStatisticsDto);

}
