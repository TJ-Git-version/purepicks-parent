package com.devsurfer.purepicks.model.dto.system.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 20:52
 * description 分配菜单新增实体
 */
@Data
@Tag(name = "分配菜单新增实体")
public class AssignMenuDto {

    @Schema(description ="角色id")
    private Long roleId;

    @Schema(description = "菜单id")
    private List<Long> menuIdList;

}
