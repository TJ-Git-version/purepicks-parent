package com.devsurfer.purepicks.manager.controller;

import com.devsurfer.purepicks.manager.service.PortalService;
import com.devsurfer.purepicks.manager.service.ValidateCodeService;
import com.devsurfer.purepicks.model.dto.system.LoginDto;
import com.devsurfer.purepicks.model.dto.system.LoginTokenDto;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.system.LoginUserInfoVo;
import com.devsurfer.purepicks.model.vo.system.LoginVo;
import com.devsurfer.purepicks.model.vo.system.ValidateCodeVo;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:09
 * description 门户接口
 */
@Tag(name = "门户接口")
@RestController
@RequestMapping("/admin/system/portal")
@AllArgsConstructor
public class PortalController {

    private final PortalService portalService;

    private final ValidateCodeService validateCodeService;

    @Operation(description = "用户登录: 使用账号密码登录")
    @PostMapping("/login-account-password")
    public ResultUtil<LoginVo> loginAccountPassword(@RequestBody LoginDto loginDto) {
        return ResultUtil.ok(portalService.loginAccountPassword(loginDto), ResultCodeEnum.SUCCESS);
    }

    @Operation(description = "获取登录用户信息")
    @PostMapping("/login-user-info")
    public ResultUtil<LoginUserInfoVo> loginUserInfo() {
        return ResultUtil.ok(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

    @Operation(description = "获取登录验证码")
    @GetMapping("/generateValidateCode")
    public ResultUtil<ValidateCodeVo> generateValidateCode() {
        return ResultUtil.ok(validateCodeService.generateValidateCode());
    }

    @Operation(description = "退出登录")
    @PostMapping("/logout")
    public ResultUtil<?> logout(@RequestBody LoginTokenDto loginTokenDto) {
        portalService.logout(loginTokenDto);
        return ResultUtil.ok();
    }

}
