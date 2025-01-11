package com.devsurfer.purepicks.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 1:33
 * description 验证码响应实体
 */
@Data
@Tag(name = "验证码响应实体")
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCodeVo {

    @Schema(description = "获取验证码key")
    private String codeKey;

    @Schema(description = "验证码图片，base64")
    private String codeValue;

}
