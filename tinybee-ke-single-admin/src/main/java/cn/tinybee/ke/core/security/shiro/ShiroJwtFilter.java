package cn.tinybee.ke.core.security.shiro;

import cn.tinybee.ke.common.util.WebUtils;
import cn.tinybee.ke.common.web.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ShiroJwtFilter extends BasicHttpAuthenticationFilter {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private String[] excludes;

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

    private static final String TOKEN_KEY = HttpHeaders.AUTHORIZATION;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.debug("isAccessAllowed===>");
        if (isLoginAttempt(request, response)) {
            try {
                return this.executeLogin(request, response);
            } catch (Exception e) {
                WebUtils.writeJson2Response((HttpServletResponse) response, ApiResult.unauthorized());
                log.debug("return false");
                return false;
            }
        } else {
            log.debug("return false");
            WebUtils.writeJson2Response((HttpServletResponse) response, ApiResult.unauthorized());
            return false;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(TOKEN_KEY);// Authorization
        log.debug("executeLogin ===> ");
        if (StringUtils.isBlank(token)) {
            token = req.getParameter("token");
        }
        ShiroJwtToken jwtToken = new ShiroJwtToken(token);
        try {
            Subject subject = getSubject(request, response);
            subject.login(jwtToken);
            return true;
        } catch (Exception e) {
            log.debug(e.getMessage());
            WebUtils.writeJson2Response((HttpServletResponse) response, ApiResult.unauthorized());
            return false;
        }
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }



    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        log.debug("isLoginAttempt===>");
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(TOKEN_KEY);// Authorization
        return token != null;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        super.postHandle(request, response);
    }
}
