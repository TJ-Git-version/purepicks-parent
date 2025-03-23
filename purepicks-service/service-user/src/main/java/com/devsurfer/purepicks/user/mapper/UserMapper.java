package com.devsurfer.purepicks.user.mapper;

import com.devsurfer.purepicks.model.dto.user.UserLoginDto;
import com.devsurfer.purepicks.model.dto.user.UserRegisterDto;
import com.devsurfer.purepicks.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 19:49
 * description TODO
 */
public interface UserMapper {


    void insertRegister(UserRegisterDto userRegisterDto);

    Long selectCount(@Param("column") String column, @Param("value") String value);

    UserInfo selectOne(@Param("column") String column, @Param("value") String value);

    void loginAccountPassword(@Param("ipAddress") String ipAddress, @Param("loginDto") UserLoginDto userLoginDto);

}
