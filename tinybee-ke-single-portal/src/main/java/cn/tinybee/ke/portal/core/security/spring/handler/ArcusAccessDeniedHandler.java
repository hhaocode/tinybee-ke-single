package cn.tinybee.ke.portal.core.security.spring.handler;

import cn.hutool.json.JSONUtil;
import cn.tinybee.ke.common.util.WebUtils;
import cn.tinybee.ke.common.web.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录访问被拒绝
 * @description 
 * @author hao.huang
 * @date 2019年11月19日
 */
@Slf4j
@Component
public class ArcusAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("BeeAccessDeniedHandler ==> start");
		log.info("accessDeniedException: {}", JSONUtil.toJsonStr(accessDeniedException));
		WebUtils.writeJson2Response(response, ApiResult.unauthorized());
		log.info("BeeAccessDeniedHandler ==> end");

	}

}
