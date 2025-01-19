package com.devsurfer.purepicks.model.dto.system.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 21:40
 * description 系统菜单删除实体
 */
@Data
@Tag(name = "系统菜单删除实体")
public class SysMenuDeleteDto {

    @Schema(description = "删除ID")
    private Long menuId;

}
