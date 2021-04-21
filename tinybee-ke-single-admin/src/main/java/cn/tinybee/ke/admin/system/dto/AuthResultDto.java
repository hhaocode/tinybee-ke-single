package cn.tinybee.ke.admin.system.dto;

import cn.tinybee.ke.admin.system.entity.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResultDto implements Serializable {

    private static final long serialVersionUID = -2604446371785407020L;
    private String token;
    private SystemUser user;

    // 菜单
}
