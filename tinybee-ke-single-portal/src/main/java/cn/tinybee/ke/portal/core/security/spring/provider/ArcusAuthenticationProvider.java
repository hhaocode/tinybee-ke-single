package cn.tinybee.ke.portal.core.security.spring.provider;

import cn.hutool.json.JSONUtil;
import cn.tinybee.ke.common.util.security.JJWTUtils;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusSpringSecurityUserHandler;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusLoginWebAuthenticationDetails;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusUser;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author hao.huang
 * @description
 * @date 2019年11月19日
 */
@Slf4j
@Component
public class ArcusAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ArcusUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ArcusSpringSecurityUserHandler tinybeeSpringSecurityUserHandler;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info(JSONUtil.toJsonStr(authentication));
        log.info("authentication{}", JSONUtil.toJsonStr(authentication.getDetails()));
//		String pwd = (String) authentication.getCredentials();
//		String account = (String) authentication.getPrincipal();
        ArcusLoginWebAuthenticationDetails details = (ArcusLoginWebAuthenticationDetails) authentication.getDetails();
        boolean isWeek = details.isWeek();
        if (isWeek) {
            //设置失效时间
        }

        switch (details.getType()) {
            case password:

                break;

            default:
                break;
        }
        String mobile = details.getMobile();
        String password = details.getPassword();
        if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
            throw new BadCredentialsException("账号和密码不能为空");
        }
        ArcusUser user = (ArcusUser) userDetailsService.loadUserByUsername(mobile);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        user.setPassword(null);
        tinybeeSpringSecurityUserHandler.setUserToRedis(JJWTUtils.PC_TOKEN_PRE + user.getUsername(), user);
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    /**
     * 支持用户密码token认证
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
