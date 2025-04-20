package com.devsurfer.purepicks.order.service.impl;

import com.devsurfer.purepicks.feign.cart.CartFeignClient;
import com.devsurfer.purepicks.feign.product.ProductFeignClient;
import com.devsurfer.purepicks.feign.user.UserFeignClient;
import com.devsurfer.purepicks.model.dto.h5.OrderInfoDto;
import com.devsurfer.purepicks.model.entity.h5.CartInfo;
import com.devsurfer.purepicks.model.entity.order.OrderInfo;
import com.devsurfer.purepicks.model.entity.order.OrderItem;
import com.devsurfer.purepicks.model.entity.order.OrderLog;
import com.devsurfer.purepicks.model.entity.user.UserAddress;
import com.devsurfer.purepicks.model.enums.log.LogOperateTypeEnum;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import com.devsurfer.purepicks.model.vo.h5.TradeVo;
import com.devsurfer.purepicks.model.vo.order.OrderInfoVo;
import com.devsurfer.purepicks.order.mapper.OrderInfoMapper;
import com.devsurfer.purepicks.order.service.OrderInfoService;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.devsurfer.purepicks.model.enums.order.OrderStatusEnum.PENDING_PAYMENT;
import static com.devsurfer.purepicks.model.enums.order.PayTypeEnum.ALI_PAY;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/30 22:42
 * description TODO
 */
@Service
@AllArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService {

    private final CartFeignClient cartFeignClient;
    private final ProductFeignClient productFeignClient;
    private final UserFeignClient userFeignClient;
    private final OrderInfoMapper orderInfoMapper;

    @Override
    public TradeVo getOrderTradeVo() {
        List<CartInfo> checkedCartList = cartFeignClient.getAllCheckedCartList();
        if (checkedCartList == null) {
            PurePicksException.error(ResultCodeEnum.CART_EMPTY_ERROR);
        }
        List<OrderItem> orderItemList = checkedCartList.stream().map(cartInfo -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setCreateTime(cartInfo.getCreateTime());
            orderItem.setUpdateTime(cartInfo.getUpdateTime());
            return orderItem;
        }).toList();
        BigDecimal totalPrice = orderItemList.stream().map(OrderItem::computerPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new TradeVo(totalPrice, orderItemList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        // 查询发货地址
        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());
        verifyOrderInfo(orderInfoDto, userAddress);
        // 订单信息
        final OrderInfo orderInfo = getOrderInfo(orderInfoDto, userAddress);
        orderInfoMapper.insert(orderInfo);
        // 订单明细
        orderInfoMapper.insertOrderItem(orderInfo.getId(), orderInfoDto.getOrderItemList());
        // 订单日志
        orderInfoMapper.insertOrderLog(OrderLog.creatLog(orderInfo.getId(), LogOperateTypeEnum.MOBILE_USER, PENDING_PAYMENT));
        // 清空选中购物车
        cartFeignClient.deleteChecked();
        return orderInfo.getId();
    }

    @Override
    public OrderInfoVo getOrderInfoById(Long orderId) {
        OrderInfoVo orderInfoVo = orderInfoMapper.findOrderInfoById(orderId);
        orderInfoVo.setOrderItemList(orderInfoMapper.findOrderItemListByOrderId(orderInfoVo.getId()));
        return orderInfoVo;
    }

    @Override
    public TradeVo buy(Long skuId) {
        final ProductSkuVo productSkuVo = productFeignClient.getBySkuId(skuId);
        if (productSkuVo == null) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_EXIST_ERROR);
        }
        final TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(productSkuVo.getSalePrice());
        final OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuNum(1);
        orderItem.setSkuPrice(productSkuVo.getSalePrice());
        orderItem.setThumbImg(productSkuVo.getThumbImage());
        orderItem.setSkuName(productSkuVo.getSkuName());
        tradeVo.setOrderItemList(List.of(orderItem));
        return tradeVo;
    }

    @Override
    public PageInfo<OrderInfoVo> myOrders(Integer page, Integer limit, Integer orderStatus) {
        final Long userId = AuthContextUtil.getAppletUserId();
        final Page<OrderInfoVo> pageInfo = PageHelper.startPage(page, limit);
        List<OrderInfoVo> orderInfoVos = orderInfoMapper.findUserOrderByUidAndStatus(userId, orderStatus);
        if (orderInfoVos.isEmpty()) {
            return pageInfo.toPageInfo();
        }
        List<OrderItem> orderItemList = orderInfoMapper.findOrderItemListInOrderId(orderInfoVos.stream().map(OrderInfoVo::getId).toList());
        if (!orderItemList.isEmpty()) {
            final Map<Long, List<OrderItem>> orderItemMap = orderItemList.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
            orderInfoVos.stream()
                    .filter(orderInfoVo -> orderItemMap.containsKey(orderInfoVo.getId()))
                    .forEach(orderInfoVo -> orderInfoVo.setOrderItemList(orderItemMap.get(orderInfoVo.getId())));
        }
        return pageInfo.toPageInfo();
    }

    private void verifyOrderInfo(OrderInfoDto orderInfoDto, UserAddress userAddress) {
        // 1.数据校验, 订单明细是否合法
        //List<CartInfo> checkedCartList = cartFeignClient.getAllCheckedCartList();
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        //Map<Long, CartInfo> skuToCartInfoMap = checkedCartList.stream().collect(Collectors.toMap(CartInfo::getSkuId, i -> i));
        //// 校验选中购物车是否合法
        //if (orderItemList.size() != checkedCartList.size() || orderItemList.stream().noneMatch(m -> skuToCartInfoMap.containsKey(m.getSkuId()))) {
        //    PurePicksException.error(ResultCodeEnum.CART_NUMBER_ERROR);
        //}
        //// 校验sku商品数量是否合法
        //if (orderItemList.stream().noneMatch(orderItem -> Objects.equals(skuToCartInfoMap.get(orderItem.getSkuId()).getSkuNum(), orderItem.getSkuNum()))) {
        //    PurePicksException.error(ResultCodeEnum.CART_NUMBER_ERROR);
        //}
        //// 校验sku商品价格是否合法
        //if (orderItemList.stream().noneMatch(orderItem -> skuToCartInfoMap.get(orderItem.getSkuId()).getCartPrice().compareTo(orderItem.getSkuPrice()) == 0)) {
        //    PurePicksException.error(ResultCodeEnum.CART_PRICE_ERROR);
        //}
        // 2.数据校验, 库存是否充足
        orderItemList.forEach(i -> {
            ProductSkuVo productSkuVo = productFeignClient.getBySkuId(i.getSkuId());
            if (productSkuVo == null) {
                PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_EXIST_ERROR);
            }
            if (productSkuVo.getStockNum() < i.getSkuNum()) {
                PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_STOCK_ERROR);
            }
            if (productSkuVo.getSalePrice().compareTo(i.getSkuPrice()) != 0) {
                PurePicksException.error(ResultCodeEnum.CART_PRICE_ERROR);
            }
        });
        if (userAddress == null) {
            PurePicksException.error(ResultCodeEnum.USER_ADDRESS_ERROR);
        }
    }

    @NotNull
    private static OrderInfo getOrderInfo(OrderInfoDto orderInfoDto, UserAddress userAddress) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(AuthContextUtil.getAppletUserId());
        orderInfo.setNickname(AuthContextUtil.getAppletUserInfo().getNickname());
        orderInfo.setOrderNo(orderInfo.getOrderNoStr());
        orderInfo.setCouponId(0L);
        orderInfo.computerTotalAmount(orderInfoDto.getOrderItemList());
        orderInfo.setCouponAmount(BigDecimal.ZERO);
        orderInfo.setFreightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(ALI_PAY.value());
        orderInfo.setOrderStatus(PENDING_PAYMENT.value());
        orderInfo.setReceiverName(userAddress.getUsername());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverPostCode("");
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getAddress());
        orderInfo.setRemark(orderInfoDto.getRemark());
        return orderInfo;
    }
}
