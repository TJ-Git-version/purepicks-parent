package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.manager.mapper.SysUserMapper;
import com.devsurfer.purepicks.manager.service.PortalService;
import com.devsurfer.purepicks.model.dto.system.LoginDto;
import com.devsurfer.purepicks.model.dto.system.LoginTokenDto;
import com.devsurfer.purepicks.model.entity.system.SysUser;
import com.devsurfer.purepicks.model.enums.RedisKeyConstant;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.LoginUserInfoVo;
import com.devsurfer.purepicks.model.vo.system.LoginVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:12
 * description TODO
 */
@Service
@AllArgsConstructor
public class PortalServiceImpl implements PortalService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final SysUserMapper sysUserMapper;

    @Override
    public LoginVo loginAccountPassword(LoginDto loginDto) {
        // 用户名和密码不能为空
        if (StrUtil.isBlank(loginDto.getUsername()) || StrUtil.isBlank(loginDto.getPassword()) || StrUtil.isBlank(loginDto.getCaptchaKey()) || StrUtil.isBlank(loginDto.getInputCaptcha())) {
            PurePicksException.error(ResultCodeEnum.PARAMETER_INVALID_ERROR);
        }
        String loginValidateCodeRedisKey = RedisKeyConstant.build(RedisKeyConstant.LOGIN_VALIDATE_CODE, loginDto.getCaptchaKey());
        String validateCode = (String) redisTemplate.opsForValue().get(loginValidateCodeRedisKey);
        // 校验验证码是否已过期
        if (StrUtil.isBlank(validateCode)) {
            PurePicksException.error(ResultCodeEnum.USER_LOGIN_VALIDATE_CODE_EXPIRE_ERROR);
        }
        // 校验验证码是否合法
        if (!Objects.equals(validateCode.toLowerCase(), loginDto.getInputCaptcha().toLowerCase())) {
            PurePicksException.error(ResultCodeEnum.USER_LOGIN_VALIDATE_CODE_ERROR);
        }

        // 验证通过在redis中删除验证码
        redisTemplate.delete(loginValidateCodeRedisKey);

        // 校验用户名是否存在
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUsername());
        if (Objects.isNull(sysUser)) {
            PurePicksException.error(ResultCodeEnum.USER_USERNAME_NOT_EXITS_ERROR);
        }
        // 校验用户状态是否禁用
        if (sysUser.getStatus() == 0) {
            PurePicksException.error(ResultCodeEnum.USER_USERNAME_FROZEN_ERROR);
        }

        // 校验密码是否合法
        String userPassword = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if (!sysUser.getPassword().equals(userPassword)) {
            PurePicksException.error(ResultCodeEnum.USER_PASSWORD_ERROR);
        }

        // 生成登录令牌
        String token = UUID.randomUUID().toString().replace("-", "");
        // 生成刷新令牌
        String refreshToken = UUID.randomUUID().toString().replace("-", "");

        // 将登录令牌保存到redis中
        redisTemplate.opsForValue().set(RedisKeyConstant.build(RedisKeyConstant.LOGIN_TOKEN, token), JSONUtil.toJsonStr(sysUser), 30, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(RedisKeyConstant.build(RedisKeyConstant.LOGIN_REFRESH_TOKEN, refreshToken), token, 35, TimeUnit.MINUTES);

        // 响应给客户端
        return new LoginVo(token, refreshToken);
    }

    @Override
    public LoginUserInfoVo loginUserInfo(LoginTokenDto loginTokenDto) {
        Object sysUser = redisTemplate.opsForValue().get(RedisKeyConstant.build(RedisKeyConstant.LOGIN_TOKEN, loginTokenDto.getToken()));
        Object refreshToken = redisTemplate.opsForValue().get(RedisKeyConstant.build(RedisKeyConstant.LOGIN_REFRESH_TOKEN, loginTokenDto.getRefreshToken()));
        if (sysUser == null) {
            return new LoginUserInfoVo();
        }
        if (!Objects.equals(refreshToken, loginTokenDto.getToken())) {
            PurePicksException.error(ResultCodeEnum.PARAMETER_INVALID_ERROR);
        }
        return BeanUtil.copyProperties(JSONUtil.toBean(JSONUtil.toJsonStr(sysUser), SysUser.class), LoginUserInfoVo.class);
    }

    @Override
    public void logout(LoginTokenDto loginTokenDto) {
        // 校验参数是否合法
        if (loginTokenDto == null || StrUtil.isBlank(loginTokenDto.getToken()) || StrUtil.isBlank(loginTokenDto.getRefreshToken())) {
            PurePicksException.error(ResultCodeEnum.PARAMETER_INVALID_ERROR);
        }
        String loginTokenRedisKey = RedisKeyConstant.build(RedisKeyConstant.LOGIN_TOKEN, loginTokenDto.getToken());
        String loginRefreshTokenRedisKey = RedisKeyConstant.build(RedisKeyConstant.LOGIN_REFRESH_TOKEN, loginTokenDto.getRefreshToken());
        redisTemplate.delete(loginTokenRedisKey);
        redisTemplate.delete(loginRefreshTokenRedisKey);
    }


}
