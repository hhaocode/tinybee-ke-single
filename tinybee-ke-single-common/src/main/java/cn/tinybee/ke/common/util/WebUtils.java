package cn.tinybee.ke.common.util;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@Slf4j
public class WebUtils {

    public static void writeJson2Response(HttpServletResponse response, Object data) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSONUtil.toJsonStr(data));
            writer.flush();
        } catch (IOException e) {
            log.info("WebUtils.writeJson2Response=> {}",e.getMessage());
        }finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public static String arguments(HttpServletRequest request) {
        final String path =
                request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern =
                request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();

        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
        return arguments;
    }
}
