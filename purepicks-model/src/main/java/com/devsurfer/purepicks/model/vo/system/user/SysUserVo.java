package com.devsurfer.purepicks.model.vo.system.user;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 20:22
 * description 系统用户响应实体
 */
@Data
@Tag(name = "系统用户响应实体")
public class SysUserVo {

    @Schema(description = "主键ID")
    private Long id;

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

    @Schema(description = "用户状态")
    private Integer status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
