package cn.tinybee.ke.portal.core.security.spring.handler;

import cn.hutool.json.JSONUtil;
import cn.tinybee.ke.common.util.WebUtils;
import cn.tinybee.ke.common.util.security.JJWTUtils;
import cn.tinybee.ke.common.web.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功的数据
 * @description 
 * @author hao.huang
 * @date 2019年11月19日
 */
@Slf4j
@Component
public class ArcusLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	private ArcusSpringSecurityUserHandler tinybeeSpringSecurityUserHandler;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("ArcusLogoutSuccessHandler ==> start");
		log.info("authentication: {}", JSONUtil.toJsonStr(authentication));
		log.info("ArcusLogoutSuccessHandler ==> end");
		String token = request.getHeader("Authorization");
		if(StringUtils.isNotBlank(token)) {
			String username = JJWTUtils.getUsernameFromToken(token);
			System.out.println(username);
			if(username != null) {
				tinybeeSpringSecurityUserHandler.removeUserInRedis(JJWTUtils.PC_TOKEN_PRE + username);
			}
			log.info("logout token:{}", token);
		}
		
		WebUtils.writeJson2Response(response, ApiResult.success("登出成功", true));
	}

}
