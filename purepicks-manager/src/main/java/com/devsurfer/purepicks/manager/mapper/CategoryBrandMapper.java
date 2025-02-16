package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.system.brand.CategoryBrandDto;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 14:40
 * description TODO
 */
public interface CategoryBrandMapper {

    void deleteCategoryBrandByBrandId(Long brandId);

    void insertBatch(CategoryBrandDto categoryBrandDto);

}
