package com.devsurfer.purepicks.model.result;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/9 22:00
 * description 封装统一结果集枚举
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(201, "系统异常"),
    PARAMETER_INVALID_ERROR(202, "参数非法"),
    FILE_SIZE_TOO_LARGE_ERROR(203, "你上传的文件太大啦,我承受不住,请重新上传哦"),

    /* 用户模块 260-300*/
    USER_USERNAME_NOT_EXITS_ERROR(260, "用户名不存在"),
    USER_USERNAME_FROZEN_ERROR(261, "当前用户已被冻结"),
    USER_PASSWORD_ERROR(262, "密码错误,请重新输入"),
    USER_LOGIN_VALIDATE_CODE_ERROR(263, "验证码错误，请重新输入"),
    USER_LOGIN_VALIDATE_CODE_EXPIRE_ERROR(264, "验证码已过期，请重新获取"),
    USER_NOT_LOGIN_ERROR(265, "令牌过期，请重新登陆"),
    USER_NAME_EXISTS_ERROR(266, "用户名已存在"),

    /* 角色模块 301~320*/
    ROLE_NAME_EXISTS_ERROR(301, "角色名称已存在,请查询输入"),
    ROLE_CODE_EXISTS_ERROR(302, "角色编号已存在,请查询输入"),
    ROLE_IS_USED_DELETE_ERROR(303, "当前角色已绑定用户,无法删除"),

    /* 文件管理 321~340*/
    FILE_IS_EMPTY_ERROR(321, "文件不能为空,请重新上传"),
    FILE_UPLOAD_ERROR(322, "文件上传失败"),
    FILE_TYPE_NOT_ENTITY_ERROR(323, "文件扩展名不存在"),
    FILE_NOT_ENTITY_ERROR(324, "该文件不存在"),
    FILE_NOT_USER_ERROR(325, "该文件已被禁用"),
    FILE_DOWNLOAD_ERROR(326, "文件下载失败,请联系管理员!"),

    /* 菜单管理 321~340*/
    MENU_NAME_EXISTS_ERROR(341, "菜单标题已存在,请查询输入"),
    MENU_CODE_EXISTS_ERROR(341, "菜单编号已存在,请查询输入"),
    MENU_DELETE_ERROR(341, "存在子菜单，删除失败"),

    /* 分类管理 351~360*/
    CATEGORY_NAME_EXISTS_ERROR(351, "分类名称已存在,请重新输入"),
    CATEGORY_DELETE_ERROR(351, "存在子菜单,删除失败"),
    ;

    private final Integer code;

    private final String message;

}
