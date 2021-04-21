package cn.tinybee.ke.common.exception;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/27
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {

    }

    public BusinessException(String message) {
        super(message);
    }

}
