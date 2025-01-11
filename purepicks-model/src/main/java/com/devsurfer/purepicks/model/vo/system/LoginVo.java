package com.devsurfer.purepicks.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:07
 * description 登录成功响应实体
 */
@Tag(name = "登录成功响应实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo {

    @Schema(description = "登录令牌")
    private String token;

    @Schema(description = "令牌失效,刷新令牌")
    private String refreshToken;

}

