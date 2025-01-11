package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.devsurfer.purepicks.manager.service.ValidateCodeService;
import com.devsurfer.purepicks.model.enums.RedisKeyConstant;
import com.devsurfer.purepicks.model.vo.system.ValidateCodeVo;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.devsurfer.purepicks.model.enums.RedisKeyConstant.LOGIN_VALIDATE_CODE;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 2:08
 * description TODO
 */
@Service
@AllArgsConstructor
public class ValidateCodeServiceImpl implements ValidateCodeService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {
        // 使用hutool工具包中的工具类生成图片验证码
        // 参数：宽  高  验证码位数 干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, 6, 20);
        // 获取验证码值
        String codeValue = circleCaptcha.getCode();
        // 获取验证码base64图片
        String imageBase64 = circleCaptcha.getImageBase64();

        // 使用uuid生成图片验证码key
        String codeKey = UUID.randomUUID().toString().replace("-", "");

        // 将验证码值存储到redis中
        redisTemplate.opsForValue().set(RedisKeyConstant.build(LOGIN_VALIDATE_CODE, codeKey), codeValue, 5, TimeUnit.MINUTES);

        return new ValidateCodeVo(codeKey, "data:image/png;base64," + imageBase64);
    }
}
