package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.ProductUnitService;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.product.ProductUnitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:26
 * description TODO
 */
@Tag(name = "商品单元管理", description = "商品单元管理")
@RestController
@RequestMapping("/admin/system/product-unit")
@AllArgsConstructor
public class ProductUnitController {

    private final ProductUnitService productUnitService;

    @Operation(summary = "获取所有商品单元信息", description = "获取所有商品单元信息")
    @GetMapping("list-all")
    public ResultUtil<List<ProductUnitVo>> listAllProductUnit() {
        return ResultUtil.ok(productUnitService.listAllProductUnit());
    }


}
