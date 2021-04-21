package cn.tinybee.ke.admin.system.service;

import cn.tinybee.ke.admin.system.dto.SystemDeptDto;
import cn.tinybee.ke.admin.system.entity.SystemDept;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 组织表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
public interface SystemDeptService extends IService<SystemDept> {

    List<SystemDeptDto> tree();

    List<SystemDeptDto> tableTree(KeywordQueryParam param);
}
