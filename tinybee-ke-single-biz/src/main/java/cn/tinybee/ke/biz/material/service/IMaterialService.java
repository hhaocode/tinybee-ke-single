package cn.tinybee.ke.biz.material.service;

import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/22 11:32
 */
public interface IMaterialService {


    Page<MaterialDto> page (Operator user, Page page, Integer type, Long groupId, String name);

}
