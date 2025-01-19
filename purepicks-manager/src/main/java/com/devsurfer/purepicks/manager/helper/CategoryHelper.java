package com.devsurfer.purepicks.manager.helper;

import com.devsurfer.purepicks.model.vo.system.category.CategoryVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:53
 * description 分类列表构造树器
 */
public class CategoryHelper {

    public static List<CategoryVo> buildTree(List<CategoryVo> categoryList) {
        List<CategoryVo> treeList = new ArrayList<>();
        for (CategoryVo categoryVo : categoryList) {
            if (categoryVo.getParentId() == 0) {
                treeList.add(findChildren(categoryVo, categoryList));
            }
        }
        return treeList;
    }

    private static CategoryVo findChildren(CategoryVo categoryVo, List<CategoryVo> treeNodes) {
        for (CategoryVo treeNode : treeNodes) {
            if (Objects.equals(categoryVo.getId(), treeNode.getParentId())) {
                categoryVo.getChildrenCategory().add(treeNode);
            }
        }
        return categoryVo;
    }

}
