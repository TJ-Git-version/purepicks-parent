package com.devsurfer.purepicks.product.controller;

import com.devsurfer.purepicks.model.dto.h5.ProductSkuDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import com.devsurfer.purepicks.model.vo.h5.ProductVo;
import com.devsurfer.purepicks.product.service.ProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/22 22:03
 * description TODO
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{page}/{limit}")
    @Operation(summary = "分页查询商品列表", description = "分页查询商品列表")
    public ResultUtil<PageInfo<ProductSkuVo>> pageProductList(@PathVariable("page") Integer page, @PathVariable Integer limit, ProductSkuDto productSkuDto) {
        return ResultUtil.ok(productService.pageProductList(page, limit, productSkuDto));
    }

    @GetMapping("/item/{skuId}")
    @Operation(summary = "获取商品详情信息", description = "获取商品详情信息")
    public ResultUtil<ProductVo> findProductItemBySkuId(
            @Parameter(name = "skuId", description = "商品skuId", required = true)
            @PathVariable("skuId") Long skuId) {
        return ResultUtil.ok(productService.findProductItemBySkuId(skuId));
    }

}
