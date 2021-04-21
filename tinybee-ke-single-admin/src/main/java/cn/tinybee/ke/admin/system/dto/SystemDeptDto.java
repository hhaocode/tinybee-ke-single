package cn.tinybee.ke.admin.system.dto;

import cn.tinybee.ke.admin.system.entity.SystemDept;
import lombok.Data;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/30
 */
@Data
public class SystemDeptDto extends SystemDept {

    private List<SystemDeptDto> children;
}
