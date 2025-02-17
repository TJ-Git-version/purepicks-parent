package com.devsurfer.purepicks.manager.task;

import cn.hutool.core.date.DateUtil;
import com.devsurfer.purepicks.manager.mapper.OrderInfoMapper;
import com.devsurfer.purepicks.manager.mapper.StatisticsMapper;
import com.devsurfer.purepicks.model.entity.order.OrderStatistics;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@AllArgsConstructor
@Slf4j
public class OrderStaticsTask {

    private final OrderInfoMapper orderInfoMapper;

    private final StatisticsMapper statisticsMapper;

    @Scheduled(cron = "0 0 2 * * ?")
    // @Scheduled(cron = "0/1 * * * * ?")
    public void orderStatics() {
        // 获取前一天订单数据
        String yesterday = DateUtil.offsetDay(DateUtil.date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.findOrderStaticsByYesterday(yesterday);
        if (orderStatistics != null) {
            statisticsMapper.insert(orderStatistics, yesterday);
            log.info("OrderStaticsTask-定时任务执行成功: 成功插入订单统计数据表: {}", orderStatistics);
        }
        log.info("OrderStaticsTask-定时任务执行成功: 但是没有找到昨天的订单数据: {}", yesterday);
    }


}
