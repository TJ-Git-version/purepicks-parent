package com.devsurfer.purepicks.manager.listener;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.devsurfer.purepicks.manager.helper.CategoryHelper;
import com.devsurfer.purepicks.manager.mapper.CategoryMapper;
import com.devsurfer.purepicks.model.entity.category.Category;
import com.devsurfer.purepicks.model.vo.system.category.CategoryExcelVo;
import com.devsurfer.purepicks.model.vo.system.category.CategoryVo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/9 19:01
 * description TODO
 */
public class ReadCategoryListener implements ReadListener<CategoryExcelVo> {

    private final CategoryMapper categoryMapper;

    private final Map<Long, Category> categoryMap;
    private final Map<String, Long> categoryNameToIdMap;

    public ReadCategoryListener(CategoryMapper categoryMapper, List<Category> categoryList) {
        this.categoryMapper = categoryMapper;
        this.categoryMap = categoryList.stream().collect(Collectors.toMap(Category::getId, category -> category));
        this.categoryNameToIdMap = CategoryHelper.buildExcelCategory(BeanUtil.copyToList(categoryList, CategoryVo.class)).stream().collect(Collectors.toMap(CategoryExcelVo::getCategoryName, CategoryExcelVo::getId));
    }

    @Override
    public void invoke(CategoryExcelVo categoryExcelVo, AnalysisContext analysisContext) {
        String categoryName = categoryExcelVo.getCategoryName();
        if (StrUtil.isNotBlank(categoryName)) {
            List<String> categoryNameList = Arrays.stream(categoryName.trim().split("/")).toList();
            StringBuilder currentPath = new StringBuilder();
            Long parentCategoryId = 0L;
            for (int i = 0; i < categoryNameList.size(); i++) {
                String name = categoryNameList.get(i);
                if (!currentPath.isEmpty()) {
                    currentPath.append("/");
                }
                currentPath.append(name);
                if (categoryNameToIdMap.containsKey(currentPath.toString())) {
                    parentCategoryId = categoryNameToIdMap.get(currentPath.toString());
                } else {
                    Category category = new Category();
                    category.setName(name);
                    category.setParentId(parentCategoryId);
                    category.setSort(1);
                    category.setStatus(categoryExcelVo.getConvertStatus());
                    categoryMapper.insertCategory(category);
                    parentCategoryId = category.getId();
                    categoryNameToIdMap.put(currentPath.toString(), category.getId());
                }
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

}
