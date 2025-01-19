package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.category.CategoryDeleteDto;
import com.devsurfer.purepicks.model.dto.system.category.CategoryInsertDto;
import com.devsurfer.purepicks.model.dto.system.category.CategoryUpdateDto;
import com.devsurfer.purepicks.model.vo.system.category.CategoryVo;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:45
 * description 分类管理接口实现
 */
public interface CategoryService {

    List<CategoryVo> findCategoryList();

    void addCategory(CategoryInsertDto categoryInsertDto);

    void editCategory(CategoryUpdateDto categoryUpdateDto);

    void removeCategory(CategoryDeleteDto categoryDeleteDto);

}
