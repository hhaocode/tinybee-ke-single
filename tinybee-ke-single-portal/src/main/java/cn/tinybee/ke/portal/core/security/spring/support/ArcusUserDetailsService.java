package cn.tinybee.ke.portal.core.security.spring.support;

import cn.tinybee.ke.common.exception.LoginFailedException;
import cn.tinybee.ke.common.util.security.JJWTUtils;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusSpringSecurityUserHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class ArcusUserDetailsService implements UserDetailsService {

	@Autowired
	private ArcusSpringSecurityUserHandler tinybeeSpringSecurityUserHandler;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("===> ArcusUserDetailsService start");
		ArcusUser tinybeeUser = null;
		try {
			tinybeeUser = tinybeeSpringSecurityUserHandler.getUserByUserName(username);
		} catch (LoginFailedException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		if(tinybeeUser == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		//tinybeeUser.getId().toString(),
		String token = JJWTUtils.generateToken(tinybeeUser.getId().toString(), username, new HashMap<String,Object>());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		tinybeeUser.setToken(token);
		tinybeeUser.setAuthorities(authorities);
		log.info("===> ArcusUserDetailsService end");
		return tinybeeUser;
	}

}
