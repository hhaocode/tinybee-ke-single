package cn.tinybee.ke.core.security.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/25
 */
public class ShiroJwtToken implements AuthenticationToken {
    private static final long serialVersionUID = -2997567076782575154L;
    private String token;
    private String expireAt;

    private Object user;

    public ShiroJwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getUser() {
        return user;
    }
}
