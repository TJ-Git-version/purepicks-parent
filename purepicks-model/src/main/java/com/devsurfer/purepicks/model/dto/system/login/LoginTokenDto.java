package com.devsurfer.purepicks.model.dto.system.login;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 21:11
 * description 登录令牌请求实体
 */
@Data
@Tag(name = "登录令牌请求实体")
public class LoginTokenDto {

    @Schema(description = "令牌")
    private String token;

    @Schema(description = "刷新令牌")
    private String refreshToken;

}
