package com.devsurfer.purepicks.model.dto.system.category;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:33
 * description 分类管理新增实体
 */
@Data
@Tag(name = "分类管理新增实体", description = "分类管理新增实体")
public class CategoryInsertDto {

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

}
