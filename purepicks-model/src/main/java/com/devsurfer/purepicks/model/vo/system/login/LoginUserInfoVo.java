package com.devsurfer.purepicks.model.vo.system.login;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 20:31
 * description 获取登录用户信息
 */
@Tag(name = "获取登录用户信息")
@Data
public class LoginUserInfoVo {

    @Schema(description = "ID")
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

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
