package com.devsurfer.purepicks.model.dto.system.role;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/18 15:24
 * description 分配角色实体
 */
@Data
@Tag(name = "分配角色实体")
public class AssignRoleDto {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "角色id")
    private List<Long> roleIdList;

}
