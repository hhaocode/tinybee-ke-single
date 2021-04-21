package cn.tinybee.ke.admin.system.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/27
 */
@Data
public class AuthParamDto implements Serializable {

    private String username;
    private String password;

    private String code;

    @NotNull(message = "认证类型不能为空")
    private LoginType type;

    public static enum LoginType {
        PWD, SMS, EMAIL
    }
}
