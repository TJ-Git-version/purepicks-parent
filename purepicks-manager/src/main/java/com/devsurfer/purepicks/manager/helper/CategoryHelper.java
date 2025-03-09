package com.devsurfer.purepicks.manager.helper;

import com.devsurfer.purepicks.model.vo.category.CategoryExcelVo;
import com.devsurfer.purepicks.model.vo.category.CategoryVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:53
 * description 分类列表构造树器
 */
public class CategoryHelper {

    // 递归操作,不推荐
    //public static List<CategoryVo> buildTree(List<CategoryVo> categoryList) {
    //    List<CategoryVo> treeList = new ArrayList<>();
    //    for (CategoryVo categoryVo : categoryList) {
    //        if (categoryVo.getParentId() == 0) {
    //            treeList.add(findChildren(categoryVo, categoryList.stream().filter(f -> f.getParentId() != 0).collect(Collectors.toList())));
    //        }
    //    }
    //    return treeList;
    //}
    //
    //private static CategoryVo findChildren(CategoryVo parentCategoryVo, List<CategoryVo> treeNodes) {
    //    for (CategoryVo treeNode : treeNodes) {
    //        if (Objects.equals(parentCategoryVo.getId(), treeNode.getParentId())) {
    //            parentCategoryVo.getChildrenCategory().add(treeNode);
    //        }
    //        if (treeNodes.stream().anyMatch(f -> Objects.equals(treeNode.getId(), f.getParentId()))) {
    //            findChildren(treeNode, treeNodes.stream().filter(f -> Objects.equals(treeNode.getId(), f.getParentId())).collect(Collectors.toList()));
    //        }
    //    }
    //    return parentCategoryVo;
    //}

    // map+循环 推荐
    public static List<CategoryVo> buildTree(List<CategoryVo> categoryList) {
        Map<Long, CategoryVo> categoryVoMap = categoryList.stream().collect(Collectors.toMap(CategoryVo::getId, categoryVo -> categoryVo));
        List<CategoryVo> treeList = new ArrayList<>();
        for (CategoryVo categoryVo : categoryList) {
            if (categoryVo.getParentId() == 0) {
                treeList.add(categoryVo);
            } else {
                CategoryVo parentCategory = categoryVoMap.get(categoryVo.getParentId());
                if (parentCategory != null) {
                    parentCategory.getChildrenCategory().add(categoryVo);
                }
            }
        }
        return treeList;
    }

    public static List<CategoryExcelVo> buildExcelCategory(List<CategoryVo> categoryList) {
        Map<Long, CategoryVo> categoryVoMap = categoryList.stream().collect(Collectors.toMap(CategoryVo::getId, categoryVo -> categoryVo));
        List<CategoryExcelVo> categoryExcelVos = new ArrayList<>();
        for (CategoryVo categoryVo : categoryList) {
            categoryExcelVos.add(new CategoryExcelVo(categoryVo.getId(), buildCategoryPath(categoryVo, categoryVoMap), categoryVo.getStatus() == 1 ? "可见" : "不可见"));
        }
        return categoryExcelVos;
    }


    private static String buildCategoryPath(CategoryVo categoryVo, Map<Long, CategoryVo> categoryVoMap) {
        if (categoryVo.getParentId() == 0) {
            // 递归操作,是父级,直接返回
            return categoryVo.getName();
        } else {
            // 递归操作,不是父级
            CategoryVo parentCategory = categoryVoMap.get(categoryVo.getParentId());
            return buildCategoryPath(parentCategory, categoryVoMap) + "/" + categoryVo.getName();
        }
    }


}
