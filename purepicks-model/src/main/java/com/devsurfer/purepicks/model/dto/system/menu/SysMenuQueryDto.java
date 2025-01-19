package com.devsurfer.purepicks.model.dto.system.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 21:24
 * description 系统菜单查询实体
 */
@Data
@Tag(name = "系统菜单查询实体")
@Builder
public class SysMenuQueryDto {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "菜单标题")
    private String title;

    @Schema(description = "菜单编号")
    private String menuCode;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "排除当前id")
    private Long notId;

}
