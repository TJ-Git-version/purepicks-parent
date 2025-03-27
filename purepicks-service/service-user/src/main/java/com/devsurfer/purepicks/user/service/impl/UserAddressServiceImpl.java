package com.devsurfer.purepicks.user.service.impl;


import com.devsurfer.purepicks.model.entity.user.UserAddress;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.devsurfer.purepicks.user.mapper.UserAddressMapper;
import com.devsurfer.purepicks.user.service.UserAddressService;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 19:42
 */
@Service
@AllArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getAppletUserId();
        if (userId == null) {
            PurePicksException.error(ResultCodeEnum.LOGIN_AUTH);
        }
        return userAddressMapper.findByUserId(userId);
    }
}
