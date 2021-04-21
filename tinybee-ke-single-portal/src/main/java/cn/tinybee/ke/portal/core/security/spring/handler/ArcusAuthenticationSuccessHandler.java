package cn.tinybee.ke.portal.core.security.spring.handler;

import cn.hutool.json.JSONUtil;
import cn.tinybee.ke.common.util.WebUtils;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * @description 认证成功的处理
 * @author hao.huang
 * @date 2019年11月19日
 */
@Slf4j
@Component
public class ArcusAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
		log.info("BeeAuthenticationSuccessHandler ==> start");
		log.info("authentication: {}", JSONUtil.toJsonStr(authentication));
		//获取认证成功的用户数据
		ArcusUser user = (ArcusUser) authentication.getPrincipal();
		//将token返回出去
		WebUtils.writeJson2Response( response, ApiResult.success("登录成功",user.getToken()));
		log.info("BeeAuthenticationSuccessHandler ==> end");
	}

}
