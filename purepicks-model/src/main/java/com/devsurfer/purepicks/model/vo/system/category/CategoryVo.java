package com.devsurfer.purepicks.model.vo.system.category;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:33
 * description 分类管理响应实体
 */
@Data
@Tag(name = "分类管理响应实体", description = "分类管理响应实体")
public class CategoryVo {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "图标")
    private String imageIcon;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态(0 不可见 | 1 可见)")
    private Integer status;

    @Schema(description = "下级列表")
    private List<CategoryVo> childrenCategory;

    public CategoryVo() {
        this.childrenCategory = new ArrayList<>();
    }
}
