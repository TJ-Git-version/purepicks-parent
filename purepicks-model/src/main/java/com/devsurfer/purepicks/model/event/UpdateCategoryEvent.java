package com.devsurfer.purepicks.model.event;

import com.devsurfer.purepicks.model.vo.category.CategoryVo;
import lombok.Getter;

import java.util.List;

/**
 * @author Dev Surfer
 */
@Getter
public class UpdateCategoryEvent {

    private final List<CategoryVo> categoryList;

    public UpdateCategoryEvent(List<CategoryVo> categoryList) {
        this.categoryList = categoryList;
    }

}
