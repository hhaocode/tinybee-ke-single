package cn.tinybee.ke.common.web;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/25
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> implements Serializable {

    public static final int FAILED_CODE = 1; // 失败
    public static final int SUCCESS_CODE = 0; // 成功
    public static final int INGNORE_CODE = -1; // 前端忽略
    public static final int SERVER_ERROR = 500; // 服务器错误
    public static final int FRONT_SKIP_AUTH_CODE = 4011; // 前端忽略登录状态

    public static final int ORDER_PAID_CODE = 50001; // 订单已支付

    private int code;
    private String message;
    private T data;
    private String uuid; // 控制请求

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ApiResult(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ApiResult(T data) {
        this.data = data;
    }

    public static ApiResult unauthorized() {
        return new ApiResult(HttpStatus.HTTP_UNAUTHORIZED, "未登录或者登录失效");
    }

    public static ApiResult paymentRequired() {
        return new ApiResult(HttpStatus.HTTP_PAYMENT_REQUIRED, "无权访问");
    }

    public static <T> ApiResult<T> success() {
        return new ApiResult(true);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult(data);
    }

    public static <T> ApiResult<T> success(String message, T data) {
        return new ApiResult(message, data);
    }

    public static <T> ApiResult<T> noFound() {
        return new ApiResult<>(HttpStatus.HTTP_NOT_FOUND, "无数据");
    }

    public static <T> ApiResult<T> failed(String message) {
        return new ApiResult<>(FAILED_CODE, message);
    }

    public static <T> ApiResult<T> ignoreErrorResult(T data) {
        return new ApiResult(INGNORE_CODE, data);
    }

    public static <T> ApiResult<T> serverError() {
        return new ApiResult<>(SERVER_ERROR, "服务器错误");
    }

    public static <T> ApiResult<T> frontSkipAuth () {
        return new ApiResult<>(FRONT_SKIP_AUTH_CODE, "未登录，跳过权限校验");
    }

}
