package com.devsurfer.purepicks.product.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.model.vo.h5.CategoryVo;
import com.devsurfer.purepicks.product.mapper.CategoryMapper;
import com.devsurfer.purepicks.product.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * @author Dev Surfer
 */
@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 查询分类树列表
     */
    @Override
    public List<CategoryVo> findCategoryTree() {
        String categoryObj = stringRedisTemplate.opsForValue().get(RedisKeyConstantEnum.APPLET_CATEGORY_TREE.getKey());
        if (StrUtil.isNotBlank(categoryObj)) {
            log.info("从redis缓存中获取所有一级分类数据");
            return JSONUtil.toList(categoryObj, CategoryVo.class);
        }
        List<CategoryVo> dbCategoryList = categoryMapper.findAll();
        List<CategoryVo> categoryList = dbCategoryList.stream().filter(categoryVo -> categoryVo.getParentId() == 0)
                .peek(parentCategory -> buildTree(parentCategory, dbCategoryList))
                .toList();
        log.info("从数据库中获取所有一级分类数据");
        stringRedisTemplate.opsForValue().set(RedisKeyConstantEnum.APPLET_CATEGORY_TREE.getKey(), JSONUtil.toJsonStr(categoryList), 7, TimeUnit.DAYS);
        return categoryList;
    }

    /**
     * 查询一级分类列表
     */
    @Override
    public List<CategoryVo> findOneCategory() {
        String cacheCategoryList = stringRedisTemplate.opsForValue().get(RedisKeyConstantEnum.APPLET_CATEGORY_TOP.getKey());
        if (StrUtil.isNotBlank(cacheCategoryList)) {
            return JSONUtil.toList(cacheCategoryList, CategoryVo.class);
        }
        List<CategoryVo> oneCategory = categoryMapper.findOneCategory();
        stringRedisTemplate.opsForValue().set(RedisKeyConstantEnum.APPLET_CATEGORY_TOP.getKey(), JSONUtil.toJsonStr(oneCategory), 7, TimeUnit.DAYS);
        return oneCategory;
    }

    private void buildTree(CategoryVo parentNode, List<CategoryVo> allCategoryList) {
        List<CategoryVo> children = allCategoryList.stream()
                .filter(categoryVo -> Objects.equals(categoryVo.getParentId(), parentNode.getId()))
                .toList();
        children.forEach(child -> buildTree(child, allCategoryList));
        parentNode.setChildren(children);
    }
}
