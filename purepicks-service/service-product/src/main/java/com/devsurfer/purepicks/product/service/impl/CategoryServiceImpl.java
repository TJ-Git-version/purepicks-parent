package com.devsurfer.purepicks.product.service.impl;

import com.devsurfer.purepicks.model.vo.h5.CategoryVo;
import com.devsurfer.purepicks.product.mapper.CategoryMapper;
import com.devsurfer.purepicks.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 22:16
 * description TODO
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<CategoryVo> findCategoryTree() {
        // redisTemplate.opsForValue()
        List<CategoryVo> categoryList = categoryMapper.findAll();
        return categoryList.stream().filter(categoryVo -> categoryVo.getParentId() == 0)
                .peek(parentCategory -> buildTree(parentCategory, categoryList))
                .toList();
    }

    private void buildTree(CategoryVo parentNode, List<CategoryVo> allCategoryList) {
        List<CategoryVo> children = allCategoryList.stream()
                .filter(categoryVo -> Objects.equals(categoryVo.getParentId(), parentNode.getId()))
                .toList();
        children.forEach(child -> buildTree(child, allCategoryList));
        parentNode.setChildren(children);
    }
}
