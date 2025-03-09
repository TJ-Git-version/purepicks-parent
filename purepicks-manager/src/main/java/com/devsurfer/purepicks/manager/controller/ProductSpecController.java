package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.ProductSpecService;
import com.devsurfer.purepicks.model.dto.product.ProductSpecDeleteDto;
import com.devsurfer.purepicks.model.dto.product.ProductSpecInsertDto;
import com.devsurfer.purepicks.model.dto.product.ProductSpecQueryDto;
import com.devsurfer.purepicks.model.dto.product.ProductSpecUpdateDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.product.ProductSpecVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商品规格管理", description = "商品规格管理")
@RestController
@RequestMapping("/admin/product-spec")
@AllArgsConstructor
public class ProductSpecController {

    private final ProductSpecService productSpecService;

    @Operation(summary = "商品规格分页查询", description = "商品规格分页查询")
    @GetMapping("page-list")
    public ResultUtil<PageInfo<ProductSpecVo>> pageList(ProductSpecQueryDto productSpecQueryDto) {
        return ResultUtil.ok(productSpecService.pageList(productSpecQueryDto));
    }

    @Operation(summary = "获取所有商品规格信息", description = "获取所有商品规格信息")
    @GetMapping("list-all")
    public ResultUtil<List<ProductSpecVo>> listAllProductSpec() {
        return ResultUtil.ok(productSpecService.listAllProductSpec());
    }


    @Operation(summary = "商品规格新增", description = "商品规格新增")
    @PostMapping
    public ResultUtil<?> saveProductSpec(@RequestBody ProductSpecInsertDto productSpecInsertDto) {
        productSpecService.insertProductSpec(productSpecInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "商品规格更新", description = "商品规格更新")
    @PutMapping
    public ResultUtil<?> editProductSpec(@RequestBody ProductSpecUpdateDto productSpecUpdateDto) {
        productSpecService.updateProductSpec(productSpecUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "商品规格删除", description = "商品规格删除")
    @DeleteMapping
    public ResultUtil<?> removeProductSpec(@RequestBody ProductSpecDeleteDto productSpecDeleteDto) {
        productSpecService.deleteProductSpec(productSpecDeleteDto);
        return ResultUtil.ok();
    }

}
