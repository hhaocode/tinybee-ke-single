package cn.tinybee.ke.portal.core.security.spring.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 用来接收数据
 *
 * @author hao.huang
 * @description
 * @date 2019年9月27日
 */
@Getter
@Slf4j
public class ArcusLoginWebAuthenticationDetails extends WebAuthenticationDetails {

    /**
     *
     */
    private static final long serialVersionUID = -1111975579726500105L;
    private boolean isWeek;
    private String email;
    private String username;
    private String mobile;
    private String authCode;
    private AuthenticationTypeEnum type;
    private String password;

    public ArcusLoginWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        if (MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType())
                || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                InputStream inputStream = request.getInputStream();
                Map<String, String> map = mapper.readValue(inputStream, Map.class);
                map.get("isWeek");
                email = map.get("email");
                username = map.get("username");
                mobile = map.get("mobile");
                authCode = map.get("authCode");
                String type = map.get("type");
                this.type = StringUtils.isBlank(type) ? AuthenticationTypeEnum.password : AuthenticationTypeEnum.valueOf(type);
                password = map.get("password");
            } catch (IOException e) {
                log.info("登录数据解析错误==》 {}", e.getMessage());
            }
        } else {
            //需要设置特殊
            request.getParameter("isWeek");
            email = request.getParameter("email");
            username = request.getParameter("username");
            mobile = request.getParameter("mobile");
            authCode = request.getParameter("authCode");
            String type = request.getParameter("type");
            this.type = StringUtils.isBlank(type) ? AuthenticationTypeEnum.password : AuthenticationTypeEnum.valueOf(type);
            password = request.getParameter("password");
        }
    }
}
