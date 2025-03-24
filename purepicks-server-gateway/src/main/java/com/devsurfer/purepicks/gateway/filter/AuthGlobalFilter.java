package com.devsurfer.purepicks.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.model.entity.user.UserInfo;
import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * 全局Filter，统一处理需要登录的请求
 * </p>
 *
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 23:30
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final RedisTemplate<String, String> redisTemplate;

    public AuthGlobalFilter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /*
    Ant 风格的路径匹配
        匹配符 	描述
        ?		匹配一个字符
        *		匹配多个字符
        **		匹配多层路径
     */
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    // 黑名单访问路径
    @Value("${auth.blackList}")
    private String blackList;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        log.info("请求路径: {}", path);

        String token = "";
        List<String> tokens = request.getHeaders().get("token");
        if (tokens != null && !tokens.isEmpty()) {
            token = tokens.get(0);
        }
        UserInfo userInfo = null;
        if (StrUtil.isNotBlank(token)) {
            String userInfoJson = redisTemplate.opsForValue().get(RedisKeyConstantEnum.build(RedisKeyConstantEnum.APPLET_LOGIN_TOKEN, token));
            userInfo = JSONUtil.toBean(userInfoJson, UserInfo.class);
        }
        // 判断哪些接口必须登录
        if (antPathMatcher.match(blackList, path) && userInfo == null) {
            ServerHttpResponse response = exchange.getResponse();
            ResultUtil<?> errorResult = ResultUtil.error(ResultCodeEnum.LOGIN_AUTH);
            byte[] errorBits = JSONUtil.toJsonStr(errorResult).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(errorBits);
            // 指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
