package com.devsurfer.purepicks.product.controller;

import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.h5.CategoryVo;
import com.devsurfer.purepicks.product.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 22:12
 * description TODO
 */
@Tag(name = "分类接口管理")
@RestController
@RequestMapping("/api/product/category")
@AllArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/findCategoryTree")
    public ResultUtil<List<CategoryVo>> findCategoryTree() {
        return ResultUtil.ok(categoryService.findCategoryTree());
    }

}
