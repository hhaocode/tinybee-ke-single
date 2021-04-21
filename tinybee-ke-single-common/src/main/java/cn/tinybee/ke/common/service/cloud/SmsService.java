package cn.tinybee.ke.common.service.cloud;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/8 10:06
 */
public interface SmsService {

    void sendSms(String target, String code, String param);
}
