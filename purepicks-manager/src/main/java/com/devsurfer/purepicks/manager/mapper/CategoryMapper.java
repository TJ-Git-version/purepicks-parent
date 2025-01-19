package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.entity.category.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:47
 * description TODO
 */
public interface CategoryMapper {
    List<Category> findCategoryList();

    Category selectByNameAndParentId(@Param("name") String name, @Param("parentId") Long parentId);

    void insertCategory(Category category);

    Category selectByNotIdNameAndParentId(@Param("id") Long id, @Param("name") String name, @Param("parentId") Long parentId);

    void updateCategory(Category category);

    Long countByParentId(Long categoryId);

    void deleteById(Long categoryId);
}
