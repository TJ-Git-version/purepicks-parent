package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.LoginDto;
import com.devsurfer.purepicks.model.dto.system.LoginTokenDto;
import com.devsurfer.purepicks.model.vo.system.LoginVo;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:12
 * description TODO
 */
public interface PortalService {

    /**
     * 登录:使用账号密码登录
     */
    LoginVo loginAccountPassword(LoginDto loginDto);

    /**
     * 退出登录
     */
    void logout(LoginTokenDto loginTokenDto);

}
