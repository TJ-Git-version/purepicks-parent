package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.devsurfer.purepicks.manager.helper.CategoryHelper;
import com.devsurfer.purepicks.manager.mapper.CategoryMapper;
import com.devsurfer.purepicks.manager.service.CategoryService;
import com.devsurfer.purepicks.model.dto.system.category.CategoryDeleteDto;
import com.devsurfer.purepicks.model.dto.system.category.CategoryInsertDto;
import com.devsurfer.purepicks.model.dto.system.category.CategoryUpdateDto;
import com.devsurfer.purepicks.model.entity.category.Category;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.category.CategoryVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:46
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> findCategoryList() {
        List<Category> categoryList = categoryMapper.findCategoryList();
        if (CollectionUtil.isEmpty(categoryList)) return null;
        return CategoryHelper.buildTree(BeanUtil.copyToList(categoryList, CategoryVo.class));
    }

    @Override
    public void addCategory(CategoryInsertDto categoryInsertDto) {
        Category category = categoryMapper.selectByNameAndParentId(categoryInsertDto.getName(), categoryInsertDto.getParentId());
        if (category != null) {
            PurePicksException.error(ResultCodeEnum.CATEGORY_NAME_EXISTS_ERROR);
        }
        categoryMapper.insertCategory(BeanUtil.copyProperties(categoryInsertDto, Category.class));
    }

    @Override
    public void editCategory(CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryMapper.selectByNotIdNameAndParentId(categoryUpdateDto.getId(), categoryUpdateDto.getName(), categoryUpdateDto.getParentId());
        if (category != null) {
            PurePicksException.error(ResultCodeEnum.CATEGORY_NAME_EXISTS_ERROR);
        }
        categoryMapper.updateCategory(BeanUtil.copyProperties(categoryUpdateDto, Category.class));
    }

    @Override
    public void removeCategory(CategoryDeleteDto categoryDeleteDto) {
        Long sonCount = categoryMapper.countByParentId(categoryDeleteDto.getCategoryId());
        if (sonCount > 0) {
            PurePicksException.error(ResultCodeEnum.CATEGORY_DELETE_ERROR);
        }
        categoryMapper.deleteById(categoryDeleteDto.getCategoryId());
    }
}
