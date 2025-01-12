package com.devsurfer.purepicks.model.dto.system.role;

import com.devsurfer.purepicks.model.dto.base.BaseQueryPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:37
 * description 系统角色查询实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name = "系统角色查询实体")
public class SysRoleQueryDto extends BaseQueryPageDto {

    @Schema(description = "查询字段")
    private String keyword;

}
