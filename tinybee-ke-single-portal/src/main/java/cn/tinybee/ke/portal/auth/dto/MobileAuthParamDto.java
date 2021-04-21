package cn.tinybee.ke.portal.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/6 16:14
 */
@Data
public class MobileAuthParamDto implements Serializable {

    private String token;

    @NotBlank(message = "请输入手机号码")
    private String mobile;

    private String imgCode;

    private String smsCode;

}
