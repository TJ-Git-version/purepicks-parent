package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.ProductService;
import com.devsurfer.purepicks.model.dto.product.ProductInsertDto;
import com.devsurfer.purepicks.model.dto.product.ProductQueryDto;
import com.devsurfer.purepicks.model.dto.product.ProductUpdateDto;
import com.devsurfer.purepicks.model.dto.product.ReviewProductDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.product.ProductVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:24
 * description TODO
 */
@Tag(name = "商品管理", description = "商品管理")
@RestController
@RequestMapping("/admin/system/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "获取商品信息分页列表")
    @GetMapping("page-list")
    public ResultUtil<PageInfo<ProductVo>> pageList(ProductQueryDto productQueryDto) {
        return ResultUtil.ok(productService.pageList(productQueryDto));
    }

    @Operation(summary = "获取商品信息", description = "获取商品信息")
    @GetMapping("/{productId}")
    public ResultUtil<ProductVo> findProductById(@PathVariable("productId") Long productId) {
        return ResultUtil.ok(productService.findProductById(productId));
    }

    @Operation(summary = "添加商品信息", description = "添加商品信息")
    @PostMapping
    public ResultUtil<?> saveProduct(@RequestBody ProductInsertDto productInsertDto) {
        productService.insertProduct(productInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "修改商品信息", description = "修改商品信息")
    @PutMapping
    public ResultUtil<?> editProduct(@RequestBody ProductUpdateDto productUpdateDto) {
        productService.editProduct(productUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "删除商品信息", description = "删除商品信息")
    @DeleteMapping("/{productId}")
    public ResultUtil<?> removeProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return ResultUtil.ok();
    }

    @Operation(summary = "商品审核", description = "商品审核")
    @PutMapping("/reviewProduct")
    public ResultUtil<?> reviewProduct(@RequestBody ReviewProductDto reviewProductDto) {
        productService.reviewProduct(reviewProductDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "商品上下架", description = "商品上下架")
    @PutMapping("/updateStatus/{productId}")
    public ResultUtil<?> updateStatus(@PathVariable("productId") Long productId,
                                      @Parameter(description = "商品状态: 1-上架 2-下架")
                                      @RequestParam("status") Integer status) {
        productService.updateStatus(productId, status);
        return ResultUtil.ok();
    }

}
