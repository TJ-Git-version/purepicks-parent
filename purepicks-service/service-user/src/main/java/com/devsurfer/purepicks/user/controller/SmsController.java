package com.devsurfer.purepicks.user.controller;

import com.devsurfer.purepicks.user.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 19:30
 * description 短信控制层
 */
@Tag(name = "短信管理")
@RestController
@RequestMapping("/api/user/sms")
@AllArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @GetMapping(value = "/sendCode/{phone}")
    @Operation(summary = "阿里云云市场-发送短信验证码", description = "发送短信验证码")
    public void sendValidateCode(@Parameter(name = "手机号", description = "用户手机号") @PathVariable String phone) {
        smsService.sendValidateCode(phone);
    }

}
