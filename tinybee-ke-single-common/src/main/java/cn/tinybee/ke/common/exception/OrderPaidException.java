package cn.tinybee.ke.common.exception;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/25 18:50
 */
public class OrderPaidException extends BusinessException {

    public OrderPaidException() {
        super();
    }

    public OrderPaidException(String message) {
        super(message);
    }
}
