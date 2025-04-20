package com.devsurfer.purepicks.user.service;

import com.devsurfer.purepicks.model.dto.user.UserLoginDto;
import com.devsurfer.purepicks.model.dto.user.UserRegisterDto;
import com.devsurfer.purepicks.model.entity.user.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 21:18
 * description TODO
 */
public interface UserService {

    void register(UserRegisterDto userRegisterDto);

    String loginAccountPassword(String ipAddress, UserLoginDto userLoginDto);

    UserInfo getCurrentUserInfo(HttpServletRequest request);

    Boolean isCollect(Long skuId);

}
