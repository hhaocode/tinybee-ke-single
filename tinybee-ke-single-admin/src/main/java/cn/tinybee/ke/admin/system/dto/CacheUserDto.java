package cn.tinybee.ke.admin.system.dto;

import cn.tinybee.ke.admin.system.entity.SystemUser;
import lombok.Data;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/27
 */
@Data
public class CacheUserDto extends SystemUser {

    private static final long serialVersionUID = 5926925891249178752L;
    private String token;
    private String loginIp;
    private List<String> permissions;
    private List<String> roles;
    private Long deptId;
}
