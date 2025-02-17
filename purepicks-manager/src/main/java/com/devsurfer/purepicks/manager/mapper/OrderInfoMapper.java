package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.entity.order.OrderStatistics;

public interface OrderInfoMapper {

    OrderStatistics findOrderStaticsByYesterday(String yesterday);

}
