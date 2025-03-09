package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.CategoryService;
import com.devsurfer.purepicks.model.dto.category.CategoryDeleteDto;
import com.devsurfer.purepicks.model.dto.category.CategoryInsertDto;
import com.devsurfer.purepicks.model.dto.category.CategoryQueryDto;
import com.devsurfer.purepicks.model.dto.category.CategoryUpdateDto;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.category.CategoryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Operation(summary = "查询分类列表", description = "查询分类列表")
    @GetMapping("/list")
    public ResultUtil<List<CategoryVo>> findCategoryList(CategoryQueryDto categoryQueryDto) {
        return ResultUtil.ok(categoryService.findCategoryList(categoryQueryDto));
    }

    @Operation(summary = "新增分类", description = "新增分类")
    @PostMapping
    public ResultUtil<?> addCategory(@RequestBody CategoryInsertDto categoryInsertDto) {
        categoryService.addCategory(categoryInsertDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "修改分类", description = "修改分类")
    @PutMapping
    public ResultUtil<?> editCategory(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        categoryService.editCategory(categoryUpdateDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "删除分类", description = "删除分类")
    @DeleteMapping
    public ResultUtil<?> removeCategory(@RequestBody CategoryDeleteDto categoryDeleteDto) {
        categoryService.removeCategory(categoryDeleteDto);
        return ResultUtil.ok();
    }

    @Operation(summary = "导入Excel分类模板", description = "导入Excel分类模版")
    @GetMapping("/import-excel-template")
    public void importExcelTemplate(HttpServletResponse response) {
        categoryService.importExcelTemplate(response);
    }

    @Operation(summary = "导入Excel分类", description = "导入Excel分类")
    @PostMapping("/import-excel")
    public void importExcel(@RequestParam("excelFile") MultipartFile excelFile, HttpServletRequest request) {
        categoryService.importExcel(excelFile);
    }

    @Operation(summary = "导出Excel分类", description = "导出Excel分类")
    @GetMapping("/export-excel")
    public void exportExcel(CategoryQueryDto categoryQueryDto, HttpServletResponse response) {
        categoryService.exportExcel(categoryQueryDto, response);
    }

}
