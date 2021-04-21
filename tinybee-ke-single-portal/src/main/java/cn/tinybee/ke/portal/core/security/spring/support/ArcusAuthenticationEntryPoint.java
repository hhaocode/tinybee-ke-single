package cn.tinybee.ke.portal.core.security.spring.support;

import cn.tinybee.ke.common.util.WebUtils;
import cn.tinybee.ke.common.web.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ArcusAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
		log.info("BeeAuthenticationEntryPoint====>");
		WebUtils.writeJson2Response( response, ApiResult.unauthorized());
		
	}

}
