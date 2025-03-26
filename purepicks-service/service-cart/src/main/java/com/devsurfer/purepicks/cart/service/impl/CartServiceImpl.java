package com.devsurfer.purepicks.cart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.devsurfer.purepicks.cart.service.CartService;
import com.devsurfer.purepicks.feign.product.ProductFeignClient;
import com.devsurfer.purepicks.model.entity.h5.CartInfo;
import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum.APPLET_CART_LIST;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/26 21:07
 * description 购物车接口实现类
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ProductFeignClient productFeignClient;

    public CartServiceImpl(RedisTemplate<String, Object> redisTemplate, ProductFeignClient productFeignClient) {
        this.redisTemplate = redisTemplate;
        this.productFeignClient = productFeignClient;
    }

    /**
     * 添加购物车
     *
     * @param skuId  商品skuId
     * @param skuNum 商品数量
     */
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        Long userId = AuthContextUtil.getAppletUserId();
        if (userId == null) {
            PurePicksException.error(ResultCodeEnum.LOGIN_AUTH);
        }
        String cartKey = getRedisCartKey(userId);
        CartInfo cartInfo = (CartInfo) redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        // 取一下最新的商品价格库存之类的
        ProductSkuVo productSkuVo = productFeignClient.getBySkuId(skuId);
        if (productSkuVo == null) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_EXIST_ERROR);
        }
        // 判断一下商品是否还有库存
        if (productSkuVo.getStockNum() <= 0) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_STOCK_ERROR);
        }
        if (cartInfo != null) {
            // 更新缓存购物车信息
            cartInfo.setCartPrice(productSkuVo.getSalePrice());
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
        } else {
            // 添加缓存购物车信息
            cartInfo = new CartInfo();
            cartInfo.setSkuId(skuId);
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuName(productSkuVo.getSkuName());
            cartInfo.setImgUrl(productSkuVo.getThumbImage());
            cartInfo.setUserId(userId);
            cartInfo.setCartPrice(productSkuVo.getCostPrice());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }
        redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), cartInfo);
        redisTemplate.expire(cartKey, 7, TimeUnit.DAYS);
    }

    /**
     * 构建购物车缓存key
     *
     * @param userId 用户id
     */
    private static String getRedisCartKey(Long userId) {
        return RedisKeyConstantEnum.build(APPLET_CART_LIST, String.valueOf(userId));
    }

    /**
     * 查询所有购物车信息
     *
     * @return 购物车列表
     */
    @Override
    public List<CartInfo> cartList() {
        return getCartInfoList();
    }

    @NotNull
    private List<CartInfo> getCartInfoList() {
        Long userId = AuthContextUtil.getAppletUserId();
        if (userId == null) {
            PurePicksException.error(ResultCodeEnum.LOGIN_AUTH);
        }
        return redisTemplate.opsForHash()
                .values(getRedisCartKey(userId))
                .stream()
                .map(cartInfo -> BeanUtil.toBean(cartInfo, CartInfo.class))
                .sorted((c1, c2) -> c2.getCreateTime().compareTo(c1.getCreateTime()))
                .toList();
    }

    @Override
    public void deleteCart(Long skuId) {
        redisTemplate.opsForHash().delete(getRedisCartKey(AuthContextUtil.getAppletUserId()), String.valueOf(skuId));
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        CartInfo cartInfo = (CartInfo) redisTemplate.opsForHash().get(getRedisCartKey(AuthContextUtil.getAppletUserId()), String.valueOf(skuId));
        if (cartInfo == null) {
            return;
        }
        cartInfo.setIsChecked(isChecked);
        cartInfo.setUpdateTime(new Date());
        redisTemplate.opsForHash().put(getRedisCartKey(AuthContextUtil.getAppletUserId()), String.valueOf(skuId), cartInfo);
    }

    @Override
    public void clearCart() {
        redisTemplate.delete(getRedisCartKey(AuthContextUtil.getAppletUserId()));
        log.info("{} 用户清空了购物车", AuthContextUtil.getAppletUserInfo().getUsername());
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        List<CartInfo> cartInfoList = getCartInfoList();
        cartInfoList.forEach(cartInfo -> {
            cartInfo.setIsChecked(isChecked);
            cartInfo.setUpdateTime(new Date());
            redisTemplate.opsForHash().put(getRedisCartKey(AuthContextUtil.getAppletUserId()), String.valueOf(cartInfo.getSkuId()), cartInfo);
        });
    }
}
