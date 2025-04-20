package com.devsurfer.purepicks.user.mapper;


import com.devsurfer.purepicks.model.entity.user.UserAddress;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 19:49
 * description TODO
 */
public interface UserAddressMapper {

    List<UserAddress> findByUserId(Long userId);

    UserAddress getUserAddress(Long userAddressId);

}




