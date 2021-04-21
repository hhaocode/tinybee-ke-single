package cn.tinybee.ke.admin.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/25 10:04
 */
@Data
public class UserPermissionParamDto {

    private Long id;
    private List<Long> roleIds;
    private List<Long> permissionIds;
}
