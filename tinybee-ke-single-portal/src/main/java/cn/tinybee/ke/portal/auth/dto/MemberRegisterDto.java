package cn.tinybee.ke.portal.auth.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@Data
public class MemberRegisterDto {

    @NotBlank(message = "请输入手机号")
    @Length(max = 64, message = "手机号不能为空64")
    private String mobile;
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 20, message = "密码请保持在8-20字符内")
    private String password;
    //    @NotBlank(message = "确认密码不能为空")
//    private String confirmPassword;
    @NotBlank(message = "验证码不能为空")
    private String authCode;
//    @NotNull(message = "认证类型不能未空")
//    private AuthenticationTypeEnum type;
}
