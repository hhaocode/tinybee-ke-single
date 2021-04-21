package cn.tinybee.ke.portal.core.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
public class ArcusUserUtils {

    /**
     * 获取当前用户
     * @return
     */
    public static ArcusCacheUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof ArcusCacheUser) {
            return (ArcusCacheUser)principal;
        }
        return null;
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public static Long getCurrentUserId() {
        ArcusCacheUser currentUser = getCurrentUser();
        if (currentUser == null) {
            return null;
        }
        return currentUser.getId();
    }
}
