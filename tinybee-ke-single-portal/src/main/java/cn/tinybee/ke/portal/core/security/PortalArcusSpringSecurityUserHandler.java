package cn.tinybee.ke.portal.core.security;

import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.common.exception.LoginFailedException;
import cn.tinybee.ke.common.service.RedisService;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusSpringSecurityUserHandler;
import cn.tinybee.ke.portal.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@Component
public class PortalArcusSpringSecurityUserHandler implements ArcusSpringSecurityUserHandler<ArcusCacheUser> {

    @Autowired
    private RedisService redisService;

    @Autowired
    private AuthService authService;

    @Override
    public ArcusCacheUser getUserByUserName(String username) throws LoginFailedException {
        UmsStudent member = authService.login(username);
        if (member == null) {
            throw new LoginFailedException("无此用户");
        }
        return new ArcusCacheUser(member.getId(), member.getUsername(), member.getPassword(), null, null, member);
    }

    @Override
    public ArcusCacheUser getUserFromRedis(String redisKey) {
        return redisService.get(redisKey ,ArcusCacheUser.class);
    }

    @Override
    public void setUserToRedis(String redisKey, ArcusCacheUser data) {
        redisService.set(redisKey, data, 7 * 24 * 60 * 60 * 1000);
    }

    @Override
    public void removeUserInRedis(String redisKey) {
        redisService.del(redisKey);
    }
}
