package cn.tinybee.ke.core.security.shiro;

import cn.tinybee.ke.common.service.RedisService;
import cn.tinybee.ke.common.util.security.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @author hao.huang
 * @description 自定义认证领域
 * @date 2019年9月4日
 */
@Slf4j
@Component
public class ArcusRealm extends AuthorizingRealm {

    private RedisService redisService;

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }


    private ShiroUserHandler shiroUserHandler;

    @Autowired
    public void setShiroUserHandler(ShiroUserHandler shiroUserHandler) {
        this.shiroUserHandler = shiroUserHandler;
    }

    @Autowired
    private ShiroConfig shiroConfig;

//    public void setShiroConfig(ShiroConfig shiroConfig) {
//        this.shiroConfig = shiroConfig;
//    }
//    @Value("${tinybee.shiro.user.key.prefix:tinybee:admin:}")
//    private String redisUserKey;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroJwtToken;
    }


    /**
     * 鉴权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        log.debug("doGetAuthorizationInfo ====> ");
        String token = (String) principals.getPrimaryPrincipal();
        String username = JWTUtils.getUsername(token);

//        SecurityUtils.getSubject().getSession().getAttribute()
        Object user = ShiroUserUtils.getCurrentUser();
//        Object userFromRedis = shiroUserHandler.getUserFromRedis(shiroConfig.getRedisUserKey() + username);
        if (user == null) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Method getPermissions = ReflectionUtils.findMethod(user.getClass(), "getPermissions");
        Object permissions = ReflectionUtils.invokeMethod(getPermissions, user);
        authorizationInfo.addStringPermissions((Collection<String>) permissions);
        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("doGetAuthenticationInfo ===> ");
        String tokenStr = (String) token.getPrincipal();
        String username = JWTUtils.getUsername(tokenStr);
        if (StringUtils.isBlank(username)) {
            throw new AuthenticationException("token校验不通过");
        }
        Object userFromRedis = shiroUserHandler.getUserFromRedis(shiroConfig.getRedisUserKey() + username);
        if (userFromRedis == null) {
            throw new AuthenticationException("token已经过期");
        }

        String loginIp = JWTUtils.getLoginIp(tokenStr);
        Method getLoginIp = ReflectionUtils.findMethod(userFromRedis.getClass(), "getLoginIp");
        Object redisLoginIp = ReflectionUtils.invokeMethod(getLoginIp, userFromRedis);
        if (StringUtils.isBlank(loginIp) || !loginIp.equals(redisLoginIp)) {
            throw new AuthenticationException("账户已在其他设备登录，请重新登录");
        }
        redisService.expire(shiroConfig.getRedisUserKey() + username, 7 * 24 * 60 * 1000);
        SecurityUtils.getSubject().getSession().setAttribute(ShiroUserUtils.SHIRO_SESSION_USER_KEY, userFromRedis);
        log.debug("doGetAuthorizationInfo====> {}", SecurityUtils.getSubject().getSession().getAttribute("userInfo"));
        return new SimpleAuthenticationInfo(tokenStr, token.getCredentials(), getName());
    }

}
