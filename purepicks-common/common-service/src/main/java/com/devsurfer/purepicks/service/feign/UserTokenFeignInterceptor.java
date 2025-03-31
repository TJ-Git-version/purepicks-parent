package com.devsurfer.purepicks.service.feign;

import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/31 22:03
 * description TODO
 */
public class UserTokenFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            PurePicksException.error(ResultCodeEnum.SYSTEM_ERROR);
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("token");
        requestTemplate.header("token", token);
    }
}
