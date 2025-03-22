package com.devsurfer.purepicks.product.mapper;

import com.devsurfer.purepicks.model.vo.h5.CategoryVo;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 20:40
 * description TODO
 */
public interface CategoryMapper {

    List<CategoryVo> findOneCategory();

    List<CategoryVo> findAll();

    CategoryVo findCategoryById(Long id);
}
