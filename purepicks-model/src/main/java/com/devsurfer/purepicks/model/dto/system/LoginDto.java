package com.devsurfer.purepicks.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:02
 * description 登录请求实体
 */
@Data
@Tag(name = "登录请求实体")
public class LoginDto {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "用户输入的验证码")
    private String inputCaptcha;

    @Schema(description = "验证码key")
    private String captchaKey;

}
