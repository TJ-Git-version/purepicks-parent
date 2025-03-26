package com.devsurfer.purepicks.feign.product;

import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/26 20:30
 * description 商品feign远程调用
 */
@FeignClient(value = "service-product")
public interface ProductFeignClient {

    @GetMapping("/api/product/getBySkuId/{skuId}")
    ProductSkuVo getBySkuId(@PathVariable Long skuId);

}
