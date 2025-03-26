package com.devsurfer.purepicks.product.controller;

import com.devsurfer.purepicks.feign.product.ProductFeignClient;
import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import com.devsurfer.purepicks.product.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/26 20:34
 * description 远程调用api实现
 */
@Tag(name = "商品服务-远程调用api实现")
@RestController
@AllArgsConstructor
public class ApiController implements ProductFeignClient {

    private final ProductService productService;

    @Override
    public ProductSkuVo getBySkuId(Long skuId) {
        return productService.getBySkuId(skuId);
    }
}
