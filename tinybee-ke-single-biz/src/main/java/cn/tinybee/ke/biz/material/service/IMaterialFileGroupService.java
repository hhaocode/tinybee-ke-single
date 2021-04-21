package cn.tinybee.ke.biz.material.service;

import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.biz.material.entity.MaterialFileGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
public interface IMaterialFileGroupService extends IService<MaterialFileGroup> {

    List<MaterialFileGroup> list(int type);
}
