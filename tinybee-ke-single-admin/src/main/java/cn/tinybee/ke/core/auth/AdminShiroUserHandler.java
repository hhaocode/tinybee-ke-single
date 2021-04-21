package cn.tinybee.ke.core.auth;

import cn.tinybee.ke.admin.system.dto.CacheUserDto;
import cn.tinybee.ke.common.service.RedisService;
import cn.tinybee.ke.core.security.shiro.ShiroUserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
@Component
public class AdminShiroUserHandler implements ShiroUserHandler<CacheUserDto> {

    @Autowired
    private RedisService redisService;
    @Override
    public CacheUserDto getUserFromRedis(String redisUserKey) {
        return redisService.get(redisUserKey, CacheUserDto.class);
    }

    @Override
    public boolean removeUserInRedis(String redisUserKey) {
        return redisService.del(redisUserKey);
    }
}
