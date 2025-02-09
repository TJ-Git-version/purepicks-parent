package com.devsurfer.purepicks.model.dto.system.category;

import com.devsurfer.purepicks.model.dto.base.Sort;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:33
 * description 分类管理查询实体
 */
@Data
@Tag(name = "分类管理查询实体", description = "分类管理查询实体")
public class CategoryQueryDto {

    @Schema(description = "菜单名称")
    private String keyword;

    @Schema(description = "状态：1 可见 | 2 不可见")
    private Integer status;

    @Schema(description = "创建时间：开始时间")
    private String createStartTime;

    @Schema(description = "创建时间：结束时间")
    private String createEndTime;

    @Schema(description = "排序规则 className：com.devsurfer.purepicks.model.entity.category.Category")
    private List<Sort> sorts;
}

