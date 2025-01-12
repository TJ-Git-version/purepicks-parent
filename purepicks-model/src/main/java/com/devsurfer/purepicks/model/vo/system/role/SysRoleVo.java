package com.devsurfer.purepicks.model.vo.system.role;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:37
 * description 系统角色响应实体
 */
@Data
@Tag(name = "系统角色响应实体")
public class SysRoleVo {

    @Schema(description = "角色ID")
    private Long id;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "名称编号")
    private String roleCode;

    @Schema(description = "角色简介")
    private String desc;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
