package com.devsurfer.purepicks.model.dto.system.role;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:37
 * description 系统角色新增实体
 */
@Data
@Tag(name = "系统角色新增实体")
public class SysRoleInsertDto {

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "名称编号")
    private String roleCode;

    @Schema(description = "角色简介")
    private String desc;

}
