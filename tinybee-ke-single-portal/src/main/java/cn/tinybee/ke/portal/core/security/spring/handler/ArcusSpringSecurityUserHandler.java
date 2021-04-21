package cn.tinybee.ke.portal.core.security.spring.handler;

import cn.tinybee.ke.common.exception.LoginFailedException;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusUser;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
public interface ArcusSpringSecurityUserHandler<T extends ArcusUser> {
    T getUserByUserName(String username) throws LoginFailedException;

    T getUserFromRedis(String redisKey);

    void setUserToRedis(String redisKey, T data);

    void removeUserInRedis(String redisKey);
}
