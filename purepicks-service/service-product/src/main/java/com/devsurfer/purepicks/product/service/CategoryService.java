package com.devsurfer.purepicks.product.service;

import com.devsurfer.purepicks.model.vo.h5.CategoryVo;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 22:16
 * description TODO
 */
public interface CategoryService {

    List<CategoryVo> findCategoryTree();

}
