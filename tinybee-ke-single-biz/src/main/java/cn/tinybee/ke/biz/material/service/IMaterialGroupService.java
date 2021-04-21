package cn.tinybee.ke.biz.material.service;

import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.entity.MaterialGroup;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
public interface IMaterialGroupService extends IService<MaterialGroup> {

    List<MaterialGroup> listByType(Integer type, boolean isContainAll);

    Boolean saveOrModify(Operator user, MaterialGroup param);

    Boolean deleteById(Operator operator, Long id);
}
