package com.devsurfer.purepicks.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.model.enums.RedisKeyConstant;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.result.ResultUtil;
import com.devsurfer.purepicks.model.vo.login.LoginUserInfoVo;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.devsurfer.purepicks.model.enums.RedisKeyConstant.LOGIN_REFRESH_TOKEN;
import static com.devsurfer.purepicks.model.enums.RedisKeyConstant.LOGIN_TOKEN;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 14:04
 * description TODO
 */
@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    public static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 到达处理器之前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方式
        String method = request.getMethod();

        // 如果是跨域预检请求，直接放行，检查是否接受跨域请求
        if ("OPTIONS".equals(method)) {
            return true;
        }

        // 获取token
        String token = request.getHeader("api-token");
        String refreshToken = request.getHeader("api-refresh-token");
        // 校验token是否存在
        if (StrUtil.isBlank(token)) {
            responseNotLogin(response);
            return false;
        }

        // 校验token是否失效
        String userInfo = (String) redisTemplate.opsForValue().get(RedisKeyConstant.build(LOGIN_TOKEN, token));
        // 校验用户信息是否为空
        if (StrUtil.isBlank(userInfo)) {
            responseNotLogin(response);
            return false;
        }
        // 如果不为空，存到localThread中
        AuthContextUtil.set(JSONUtil.toBean(userInfo, LoginUserInfoVo.class));

        // 获取刷新token令牌
        String refreshTokenValue = (String) redisTemplate.opsForValue().get(RedisKeyConstant.build(LOGIN_REFRESH_TOKEN, refreshToken));
        // 校验刷新token是否合法,合法真刷新当前用户token失效,更新为30分钟
        if (StrUtil.isNotBlank(refreshTokenValue) && Objects.equals(refreshTokenValue, token)) {
            redisTemplate.expire(RedisKeyConstant.build(LOGIN_TOKEN, token), 30, TimeUnit.DAYS);
            redisTemplate.expire(RedisKeyConstant.build(LOGIN_REFRESH_TOKEN, refreshToken), 35, TimeUnit.DAYS);
        }
        // 放行
        return true;
    }

    private void responseNotLogin(HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JSONUtil.toJsonStr(ResultUtil.error(ResultCodeEnum.USER_NOT_LOGIN_ERROR)));
        } catch (IOException e) {
            logger.error("response not login：{}", e.getMessage());
        }
        //PurePicksException.error(ResultCodeEnum.USER_NOT_LOGIN_ERROR);
    }

    /**
     * 处理器处理完成之后，后置拦截
     * 处理器出现异常不会执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 处理器无论是否发生异常，都会执行
     * 常用与释放资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.remove();
    }
}
