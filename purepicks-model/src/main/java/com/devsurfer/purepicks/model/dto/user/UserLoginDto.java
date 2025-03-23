package com.devsurfer.purepicks.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 22:12
 * description 会员登录dto
 */
@Data
public class UserLoginDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;


}
