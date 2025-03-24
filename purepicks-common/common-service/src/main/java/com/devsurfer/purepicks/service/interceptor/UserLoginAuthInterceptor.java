package com.devsurfer.purepicks.service.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.model.entity.user.UserInfo;
import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/24 22:25
 * description 用户登录拦截器: 将用户信息存储到ThreadLocal
 */
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, String> redisTemplate;

    public UserLoginAuthInterceptor(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String userInfoJson =  redisTemplate.opsForValue().get(RedisKeyConstantEnum.build(RedisKeyConstantEnum.APPLET_LOGIN_TOKEN, token));
        if (StrUtil.isNotBlank(userInfoJson)) {
            UserInfo userInfo = JSONUtil.toBean(userInfoJson, UserInfo.class);
            AuthContextUtil.setAppletUserInfo(userInfo);
        }
        return true;
    }

    /**
     * 在请求响应之前, 清除ThreadLocal信息, 因为ThreadLocal会为每个线程开闭一个空间, 不会自己释放资源
     * 否则线程返回到线程池, 有可能不被清除, 在下一个请求来时, 会保留上一个请求用户的信息, 造成安全问题
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.removeAppletUserInfo();
    }
}
