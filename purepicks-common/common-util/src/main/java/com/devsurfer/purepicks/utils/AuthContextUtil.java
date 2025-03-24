package com.devsurfer.purepicks.utils;

import com.devsurfer.purepicks.model.entity.user.UserInfo;
import com.devsurfer.purepicks.model.vo.login.LoginUserInfoVo;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 13:58
 * description 获取用户上下文信息
 */
public final class AuthContextUtil {

    // 存储当前线程用户信息
    private static final ThreadLocal<LoginUserInfoVo> threadLocal = new ThreadLocal<>();

    /**
     * 设置线程信息
     */
    public static void set(LoginUserInfoVo loginUserInfoVo) {
        threadLocal.set(loginUserInfoVo);
    }

    /**
     * 获取线程信息
     */
    public static LoginUserInfoVo get() {
        return threadLocal.get();
    }

    /**
     * 获取用户id
     */
    public static Long getUserId() {
        return get().getId();
    }

    /**
     * 最后一定要移除用户信息，防止资源无法释放，导致程序挂掉
     */
    public static void remove() {
        threadLocal.remove();
    }

    // --------------------------------H5端本地存储用户信息----------------------------------

    private static final ThreadLocal<UserInfo> appletThreadLocal = new ThreadLocal<>();

    public static void setAppletUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        appletThreadLocal.set(userInfo);
    }

    public static UserInfo getAppletUserInfo() {
        return appletThreadLocal.get();
    }

    public static Long getAppletUserId() {
        UserInfo userInfo = appletThreadLocal.get();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getId();
    }

    public static void removeAppletUserInfo() {
        appletThreadLocal.remove();
    }

}
