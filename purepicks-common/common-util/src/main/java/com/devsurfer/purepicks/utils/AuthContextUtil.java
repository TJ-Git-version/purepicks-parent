package com.devsurfer.purepicks.utils;

import com.devsurfer.purepicks.model.vo.system.LoginUserInfoVo;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 13:58
 * description 获取用户上下文信息
 */
public final class AuthContextUtil {

    // 存储当前线程用户信息
    public static final ThreadLocal<LoginUserInfoVo> threadLocal = new ThreadLocal<>();

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

}
