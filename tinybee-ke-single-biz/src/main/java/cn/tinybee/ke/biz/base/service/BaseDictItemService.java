package cn.tinybee.ke.biz.base.service;

import cn.tinybee.ke.biz.base.entity.BaseDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典子项表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
public interface BaseDictItemService extends IService<BaseDictItem> {

    boolean saveBatch(List<BaseDictItem> list, Long dictId, Long currentUserId);

    List<BaseDictItem> dictItems(Long dictId);
}

