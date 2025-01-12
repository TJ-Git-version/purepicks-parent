package com.devsurfer.purepicks.model.entity.system;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 14:36
 * description 系统用户实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    // 用户名
    private String username;

    // 昵称
    private String nickname;

    // 密码
    private String password;

    // 手机号
    private String phone;

    // 头像
    private String avatar;

    // 个人简介
    private String desc;

    // 用户状态
    private Integer status;

}
