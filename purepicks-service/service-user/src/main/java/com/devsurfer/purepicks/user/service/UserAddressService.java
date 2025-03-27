package com.devsurfer.purepicks.user.service;

import com.devsurfer.purepicks.model.entity.user.UserAddress;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 19:42
 */
public interface UserAddressService {

    List<UserAddress> findUserAddressList();

}
