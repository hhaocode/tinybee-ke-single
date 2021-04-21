package cn.tinybee.ke.portal.core.security.spring.handler;

import cn.hutool.json.JSONUtil;
import cn.tinybee.ke.common.util.WebUtils;
import cn.tinybee.ke.common.web.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败后的处理
 * @description 
 * @author hao.huang
 * @date 2019年11月19日
 */
@Slf4j
@Component
public class ArcusAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
		log.info("BeeAuthenticationFailureHandler ==> start");
		log.info("exception: {}", JSONUtil.toJsonStr(exception));
		WebUtils.writeJson2Response(response, ApiResult.failed(exception.getMessage()));
		log.info("BeeAuthenticationFailureHandler ==> end");

	}

}
