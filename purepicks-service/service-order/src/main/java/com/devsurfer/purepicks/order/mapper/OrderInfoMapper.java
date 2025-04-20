package com.devsurfer.purepicks.order.mapper;

import com.devsurfer.purepicks.model.entity.order.OrderInfo;
import com.devsurfer.purepicks.model.entity.order.OrderItem;
import com.devsurfer.purepicks.model.entity.order.OrderLog;
import com.devsurfer.purepicks.model.vo.order.OrderInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/4/20 13:49
 * description TODO
 */
public interface OrderInfoMapper {

    void insert(OrderInfo orderInfo);

    void insertOrderItem(@Param("orderId") Long orderId, @Param("list") List<OrderItem> orderItemList);

    void insertOrderLog(OrderLog orderLog);

    OrderInfoVo findOrderInfoById(Long orderId);

    List<OrderItem> findOrderItemListByOrderId(Long orderId);

    List<OrderInfoVo> findUserOrderByUidAndStatus(@Param("userId") Long userId, @Param("orderStatus") Integer orderStatus);

    List<OrderItem> findOrderItemListInOrderId(List<Long> list);

}
