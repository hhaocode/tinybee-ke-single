package cn.tinybee.ke.portal.core.security.spring.filter;

import cn.tinybee.ke.common.util.security.JJWTUtils;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusSpringSecurityUserHandler;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private ArcusSpringSecurityUserHandler tinybeeSpringSecurityUserHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.debug("JwtAuthenticationTokenFilter===>");
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
//        String parameter = request.getParameter("x-access-token");
        log.debug("token===>{}", token);
        if (StringUtils.isNotBlank(token) && JJWTUtils.verify(token)) {
            String username = null;
            try {
                username = JJWTUtils.getUsernameFromToken(token);
            } catch (Exception e) {
                log.info("token无效");
            }
            log.debug("username:{}", username);
            if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                ArcusUser user = tinybeeSpringSecurityUserHandler.getUserFromRedis(JJWTUtils.PC_TOKEN_PRE + username);
                if (user != null && token.equals(user.getToken())) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
