package com.devsurfer.purepicks.product.controller;

import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.brand.BrandVo;
import com.devsurfer.purepicks.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dev Surfer
 */
@Tag(name = "品牌管理")
@RestController
@RequestMapping("/api/product/brand")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有品牌", description = "查询所有品牌")
    public ResultUtil<List<BrandVo>> findBrandAll() {
        return ResultUtil.ok(brandService.findBrandAll());
    }

}
