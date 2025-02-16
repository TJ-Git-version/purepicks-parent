package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.BrandService;
import com.devsurfer.purepicks.model.dto.system.brand.*;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.system.brand.BrandVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "品牌管理", description = "品牌管理")
@RestController
@RequestMapping("/admin/brand")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @Operation(summary = "品牌信息分页查询", description = "品牌信息分页查询")
    @GetMapping("page-list")
    public ResultUtil<PageInfo<BrandVo>> pageList(BrandQueryDto brandQueryDto) {
        return ResultUtil.ok(brandService.pageList(brandQueryDto));
    }

    @Operation(summary = "品牌信息新增", description = "品牌信息新增")
    @PostMapping
    public ResultUtil<?> saveBrand(@RequestBody BrandInsertDto brandInsertDto) {
        brandService.insertBrand(brandInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "品牌信息更新", description = "品牌信息更新")
    @PutMapping
    public ResultUtil<?> editBrand(@RequestBody BrandUpdateDto brandUpdateDto) {
        brandService.updateBrand(brandUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "品牌信息批量删除", description = "品牌信息批量删除")
    @DeleteMapping
    public ResultUtil<?> removeBrand(@RequestBody BrandDeleteDto brandDeleteDto) {
        brandService.deleteBrand(brandDeleteDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "分类品牌关联", description = "分类品牌关联")
    @PostMapping("/category-brand")
    public ResultUtil<?> saveCategoryBrand(@RequestBody CategoryBrandDto categoryBrandDto) {
        brandService.insertCategoryBrand(categoryBrandDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "根据分类查询品牌", description = "根据分类查询品牌")
    @GetMapping("/list-brand-by-category-id/{categoryId}")
    public ResultUtil<List<BrandVo>> listBrandByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return ResultUtil.ok(brandService.listBrandByCategoryId(categoryId));
    }

}
