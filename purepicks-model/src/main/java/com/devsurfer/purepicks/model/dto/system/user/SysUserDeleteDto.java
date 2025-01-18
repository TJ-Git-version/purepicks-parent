package com.devsurfer.purepicks.model.dto.system.user;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 20:25
 * description 系统用户删除实体
 */
@Data
@Tag(name = "系统用户删除实体")
public class SysUserDeleteDto {

    @Schema(description = "删除主键ID")
    private List<Long> roleIds;

}
