package cn.tinybee.ke.admin.system.dto;

import cn.tinybee.ke.admin.system.entity.SystemPermission;
import lombok.Data;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/28
 */
@Data
public class SystemPermissionDto extends SystemPermission {

    private List<SystemPermissionDto> children;
}
