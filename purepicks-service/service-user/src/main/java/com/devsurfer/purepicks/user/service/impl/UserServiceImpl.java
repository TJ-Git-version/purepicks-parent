package com.devsurfer.purepicks.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.model.constant.SaleConstant;
import com.devsurfer.purepicks.model.dto.user.UserLoginDto;
import com.devsurfer.purepicks.model.dto.user.UserRegisterDto;
import com.devsurfer.purepicks.model.entity.user.UserInfo;
import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.devsurfer.purepicks.user.mapper.UserMapper;
import com.devsurfer.purepicks.user.service.UserService;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 21:18
 * description 会员用户业务层实现
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserMapper userMapper;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        String codeRedisKey = RedisKeyConstantEnum.build(RedisKeyConstantEnum.PHONE_VALIDATE_CODE, userRegisterDto.getUsername());
        String validCode = redisTemplate.opsForValue().get(codeRedisKey);
        // 验证码已失效
        if (StrUtil.isBlank(validCode)) {
            PurePicksException.error(ResultCodeEnum.VERIFICATION_CODE_EMPTY_ERROR);
        }
        // 验证码错误
        String inputCode = userRegisterDto.getCode();
        if (validCode.equalsIgnoreCase(inputCode)) {
            PurePicksException.error(ResultCodeEnum.VERIFICATION_CODE_ERROR);
        }
        // 用户名为空
        if (StringUtils.isAnyBlank(userRegisterDto.getUsername(), userRegisterDto.getPassword())) {
            PurePicksException.error(ResultCodeEnum.PARAMETER_INVALID_ERROR);
        }
        // 验证当前手机号是否注册
        String username = userRegisterDto.getUsername();
        Long usernameCount = userMapper.selectCount("username", username);
        if (usernameCount != null && usernameCount > 0) {
            PurePicksException.error(ResultCodeEnum.USER_NAME_EXISTS_ERROR);
        }
        // 密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex(StrUtil.format(SaleConstant.SALT_PASSWORD, userRegisterDto.getPassword()).getBytes(StandardCharsets.UTF_8));
        userRegisterDto.setPassword(encryptPassword);
        userMapper.insertRegister(userRegisterDto);
        redisTemplate.delete(codeRedisKey);
    }

    @Override
    public String loginAccountPassword(String ipAddress, UserLoginDto userLoginDto) {
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        if (StringUtils.isAnyBlank(ipAddress, username, password)) {
            PurePicksException.error(ResultCodeEnum.PARAMETER_INVALID_ERROR);
        }
        // 验证用户是否存在
        UserInfo userInfo = userMapper.selectOne(UserInfo.COL_USERNAME, username);
        if (userInfo == null) {
            PurePicksException.error(ResultCodeEnum.USER_USERNAME_NOT_EXITS_ERROR);
        }
        // 校验用户状态
        if (userInfo.getStatus() == 0) {
            PurePicksException.error(ResultCodeEnum.USER_USERNAME_FROZEN_ERROR);
        }
        // 校验密码是否正确
        String encryptPassword = DigestUtils.md5DigestAsHex(StrUtil.format(SaleConstant.SALT_PASSWORD, userLoginDto.getPassword()).getBytes(StandardCharsets.UTF_8));
        if (!userInfo.getPassword().equals(encryptPassword)) {
            PurePicksException.error(ResultCodeEnum.USER_PASSWORD_ERROR);
        }
        userMapper.loginAccountPassword(ipAddress, userLoginDto);
        // 信息脱敏
        userInfo.setPassword("");
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(RedisKeyConstantEnum.build(RedisKeyConstantEnum.APPLET_LOGIN_TOKEN, token), JSONUtil.toJsonStr(userInfo), 30, TimeUnit.DAYS);
        return token;
    }

    @Override
    public UserInfo getCurrentUserInfo(HttpServletRequest request) {
        // 优化一波, 从ThreadLocal中获取用户信息
        //if (request == null || request.getHeader("token") == null) {
        //    PurePicksException.error(ResultCodeEnum.LOGIN_AUTH);
        //}
        //String token = request.getHeader("token");
        //String userInfoJson = redisTemplate.opsForValue().get(RedisKeyConstantEnum.build(RedisKeyConstantEnum.APPLET_LOGIN_TOKEN, token));
        //return JSONUtil.toBean(userInfoJson, UserInfo.class);
        return AuthContextUtil.getAppletUserInfo();
    }

    @Override
    public Boolean isCollect(Long skuId) {
        return true;
    }
}
