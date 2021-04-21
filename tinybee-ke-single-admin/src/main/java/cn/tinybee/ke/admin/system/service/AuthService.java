package cn.tinybee.ke.admin.system.service;


import cn.hutool.crypto.SecureUtil;
import cn.tinybee.ke.admin.system.dto.AuthParamDto;
import cn.tinybee.ke.admin.system.dto.AuthResultDto;
import cn.tinybee.ke.admin.system.dto.CacheUserDto;
import cn.tinybee.ke.admin.system.entity.SystemUser;
import cn.tinybee.ke.common.exception.LoginFailedException;
import cn.tinybee.ke.common.service.RedisService;
import cn.tinybee.ke.common.util.security.JWTUtils;
import cn.tinybee.ke.core.security.shiro.ShiroConfig;
import cn.tinybee.ke.core.security.shiro.ShiroUserHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
@Slf4j
@Service
public class AuthService {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private ShiroConfig shiroConfig;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ShiroUserHandler<CacheUserDto> shiroUserHandler;

    @Autowired
    private Mapper mapper;


    public AuthResultDto login(AuthParamDto param, HttpServletRequest request) {
        AuthParamDto.LoginType type = param.getType();

        switch (type) {
            case PWD:
//                iMgtUserService.getUserByMobile()
            case SMS:

            case EMAIL:


        }
        return null;
    }

    public AuthResultDto login(String username, String password, HttpServletRequest request) throws LoginFailedException {
        // 获取用户信息
        SystemUser user = systemUserService.getAdministractorByUserName(username);
        if (user == null) {
            throw new LoginFailedException("用户不存在");
        }
        if (!SecureUtil.md5(password).equals(user.getPassword())) {
            throw new LoginFailedException("密码错误");
        }
        user.setPassword(null);

        String remoteHost = request.getRemoteHost();
        String remoteAddr = request.getRemoteAddr();
        log.info("remoteHost:{} ", remoteHost);
        log.info("remoteAddr:{} ", remoteAddr);
        String tokenStr = JWTUtils.generateToken(username, remoteAddr);
        CacheUserDto cacheUser = wrapperUser2CacheUser(user, tokenStr, remoteAddr);
        // 更新登录时间
        systemUserService.updateLastLoginInfo(remoteAddr, cacheUser.getId());
        // 设置
        AuthResultDto res = new AuthResultDto();
        res.setUser(cacheUser);
        res.setToken(tokenStr);
        return res;
    }
    
    public CacheUserDto wrapperUser2CacheUser (SystemUser user, String tokenStr, String loginIp) {
        CacheUserDto cacheUser = mapper.map(user, CacheUserDto.class);
        // 权限 角色 token
        cacheUser.setPermissions(systemUserService.getUserPermissions(cacheUser, null).stream().filter(v -> v.getPermissionCode() != null && StringUtils.isNotBlank(v.getPermissionCode())).map(v -> v.getPermissionCode()).collect(Collectors.toList()));
        cacheUser.setRoles(systemUserService.getUserRoles(user.getId()).stream().map(v -> v.getPermissionCode()).collect(Collectors.toList()));
        cacheUser.setToken(tokenStr);
        cacheUser.setLoginIp(loginIp);
        redisService.set(shiroConfig.getRedisUserKey() + user.getUsername(), cacheUser, RedisService.EXPRIE_TIME);
        return cacheUser;
    }

    public boolean logout(String username, HttpServletRequest request) {
        String redisUserKey = shiroConfig.getRedisUserKey() + username;
        CacheUserDto userFromRedis = shiroUserHandler.getUserFromRedis(redisUserKey);
        if (userFromRedis == null) {
            return true;
        }
        String remoteHost = request.getRemoteHost();
        String remoteAddr = request.getRemoteAddr();
        log.info("remoteHost:{} ", remoteHost);
        log.info("remoteAddr:{} ", remoteAddr);
        if (remoteAddr.equals(userFromRedis.getLoginIp())) {
            return shiroUserHandler.removeUserInRedis(redisUserKey);
        }
        return true;
    }

}
