package cn.tinybee.ke.common.exception;

import cn.tinybee.ke.common.web.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/31
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     *
     * BusinessException 业务粗哦呜 500
     * LoginFailedException 登录失败 401
     * NoDataFoundException 未找到数据 404
     * 参数校验错误 400
     *
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult<Boolean> exceptionHandler(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        log.error("exception {}", e.getClass().getName());
        log.error(e.getMessage());
        if (e instanceof BusinessException || e instanceof LoginFailedException ) {
            return ApiResult.failed(e.getMessage() );
        }else if ( e instanceof NoDataFoundException) {
            return ApiResult.noFound();
        } else if (e instanceof MissingServletRequestParameterException) {
            return ApiResult.failed("必填项为空，请提交数据");
        } else if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            StringBuilder resMessage = new StringBuilder();
            bindException.getBindingResult().getAllErrors().stream().forEach(v -> resMessage.append(v.getDefaultMessage() + ";"));
            return ApiResult.failed(resMessage.toString());
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) e;
            List<String> collect = validException.getBindingResult().getAllErrors().stream().map(v -> v.getDefaultMessage()).collect(Collectors.toList());
            return ApiResult.failed(collect.toString());
        }
        else if (e instanceof UnauthorizedException) {
            return ApiResult.failed("您无权操作");
        }
        else{
            return ApiResult.serverError();
        }
    }

}
