package com.devsurfer.purepicks.model.dto.system.user;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 20:22
 * description 系统用户新增实体
 */
@Data
@Tag(name = "系统用户新增实体")
public class SysUserInsertDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "个人简介")
    private String desc;

}
