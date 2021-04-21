package cn.tinybee.ke.biz.base.service;

import cn.tinybee.ke.biz.base.entity.BaseDict;
import cn.tinybee.ke.biz.base.entity.BaseDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
public interface BaseDictService extends IService<BaseDict> {

    boolean saveOrUpdate(BaseDict dict, Long userId);

    boolean deleteById(Long id);

    List<BaseDictItem> dictItems(String dictCode);
}
