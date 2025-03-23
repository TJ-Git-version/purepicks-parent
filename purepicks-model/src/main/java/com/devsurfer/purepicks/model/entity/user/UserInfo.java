package com.devsurfer.purepicks.model.entity.user;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfo extends BaseEntity implements Serializable {

    /**
     * 微信登录唯一标识
     */
    private String openId;

    /**
     * 微信开放平台unionID
     */
    private String unionId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 性别: 0 男生, 1 女生
     */
    private Integer sex;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 状态: 1 正常, 0 封号
     */
    private Integer status;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public static final String COL_USERNAME = "username";
}
