package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.CategoryService;
import com.devsurfer.purepicks.model.dto.system.category.CategoryDeleteDto;
import com.devsurfer.purepicks.model.dto.system.category.CategoryInsertDto;
import com.devsurfer.purepicks.model.dto.system.category.CategoryUpdateDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.system.category.CategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:25
 * description 分类管理
 */
@Tag(name = "分类管理", description = "分类管理")
@RestController
@RequestMapping("/admin/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "查询菜单列表", description = "查询菜单列表")
    @GetMapping("/list")
    public ResultUtil<List<CategoryVo>> findCategoryList() {
        return ResultUtil.ok(categoryService.findCategoryList());
    }

    @Operation(summary = "新增菜单", description = "新增菜单")
    @PostMapping
    public ResultUtil<?> addCategory(@RequestBody CategoryInsertDto categoryInsertDto) {
        categoryService.addCategory(categoryInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "修改菜单", description = "修改菜单")
    @PutMapping
    public ResultUtil<?> editCategory(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        categoryService.editCategory(categoryUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "删除菜单", description = "删除菜单")
    @DeleteMapping
    public ResultUtil<?> removeCategory(@RequestBody CategoryDeleteDto categoryDeleteDto) {
        categoryService.removeCategory(categoryDeleteDto);
        return ResultUtil.ok();
    }

}
