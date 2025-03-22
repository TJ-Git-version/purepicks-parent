package com.devsurfer.purepicks.product.controller;

import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.h5.CategoryVo;
import com.devsurfer.purepicks.product.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dev Surfer
 */
@Tag(name = "分类接口管理")
@RestController
@RequestMapping("/api/product/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/findCategoryTree")
    public ResultUtil<List<CategoryVo>> findCategoryTree() {
        return ResultUtil.ok(categoryService.findCategoryTree());
    }

}
