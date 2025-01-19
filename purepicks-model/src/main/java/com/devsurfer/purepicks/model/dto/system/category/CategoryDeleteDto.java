package com.devsurfer.purepicks.model.dto.system.category;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:33
 * description 分类管理删除实体
 */
@Data
@Tag(name = "分类管理删除实体", description = "分类管理删除实体")
public class CategoryDeleteDto {

    @Schema(description = "删除ID")
    private Long categoryId;

}
