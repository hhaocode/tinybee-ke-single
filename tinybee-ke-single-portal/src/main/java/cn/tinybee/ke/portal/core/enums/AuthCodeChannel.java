package cn.tinybee.ke.portal.core.enums;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/8 9:51
 */
public enum AuthCodeChannel {

    LOGIN("SMS_201682342"), REGISTER("SMS_201717312");

    private String code;

    AuthCodeChannel (String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
