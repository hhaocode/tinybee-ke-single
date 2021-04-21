package cn.tinybee.ke.biz.ums.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/6 15:39
 */
@Data
public class UserPasswordDto implements Serializable {

    @NotBlank(message = "请输入原密码")
    private String password;

    @NotBlank(message = "请输入新密码")
    private String newPassword;

    @NotBlank(message = "请确认新密码")
    private String confirmNewPassword;

    private String token;
}
