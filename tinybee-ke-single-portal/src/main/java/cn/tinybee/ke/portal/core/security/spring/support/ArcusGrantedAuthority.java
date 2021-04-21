package cn.tinybee.ke.portal.core.security.spring.support;

import org.springframework.security.core.GrantedAuthority;

public class ArcusGrantedAuthority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8153123771268049699L;
	private String authority;
	
	@Override
	public String getAuthority() {
		return authority;
	}

}
