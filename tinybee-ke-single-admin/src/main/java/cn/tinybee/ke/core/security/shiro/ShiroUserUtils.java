package cn.tinybee.ke.core.security.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

@Slf4j
public class ShiroUserUtils {
    public static final String SHIRO_SESSION_USER_KEY = "shiro_session_user_key";

    public static Object getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            throw new AuthenticationException("认证已经失效,请重新登录");
        }
        Object user = subject.getSession().getAttribute(SHIRO_SESSION_USER_KEY);
        if (user == null) {
            throw new AuthenticationException("认证已经失效,请重新登录");
        }
        return user;
    }

}
