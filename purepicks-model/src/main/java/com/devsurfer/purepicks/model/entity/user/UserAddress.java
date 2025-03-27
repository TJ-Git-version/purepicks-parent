package com.devsurfer.purepicks.model.entity.user;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/27 22:00
 * description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserAddress extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userid;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址标签
     */
    private String tagName;

    /**
     * 省份编号
     */
    private String provinceCode;

    /**
     * 市区编号
     */
    private String cityCode;

    /**
     * 县区编号
     */
    private String districtCode;

    /**
     * 门牌号地址
     */
    private String address;

    /**
     * 完整收货地址
     */
    private String fullAddress;

    /**
     * 是否为默认地址: 0 默认地址 | 1 普通地址
     */
    private Integer isDefault;

}
