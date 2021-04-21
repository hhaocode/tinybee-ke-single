package cn.tinybee.ke.core.security.shiro;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
public interface ShiroUserHandler<T> {


    T getUserFromRedis(String redisUserKey);


    boolean removeUserInRedis(String redisUserKey);
}
