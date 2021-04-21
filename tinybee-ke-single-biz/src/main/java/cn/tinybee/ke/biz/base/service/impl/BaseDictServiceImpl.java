package cn.tinybee.ke.biz.base.service.impl;

import cn.tinybee.ke.biz.base.entity.BaseDict;
import cn.tinybee.ke.biz.base.entity.BaseDictItem;
import cn.tinybee.ke.biz.base.mapper.BaseDictMapper;
import cn.tinybee.ke.biz.base.service.BaseDictItemService;
import cn.tinybee.ke.biz.base.service.BaseDictService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
@Service
public class BaseDictServiceImpl extends ServiceImpl<BaseDictMapper, BaseDict> implements BaseDictService {

    @Autowired
    private BaseDictItemService baseDictItemService;

    @Transactional
    @Override
    public boolean saveOrUpdate(BaseDict dict, Long userId) {
        if (dict.getId() == null) {
            dict.setAvailable(true);
            dict.setCreateTime(LocalDateTime.now());
            dict.setCreator(userId);
        } else {
            dict.setModifyTime(LocalDateTime.now());
            dict.setModifier(userId);
        }
        return this.saveOrUpdate(dict);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Long id) {
        this.removeById(id);
        QueryWrapper<BaseDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_id", id);
        return baseDictItemService.remove(queryWrapper);
    }

    @Override
    public List<BaseDictItem> dictItems(String dictCode) {
        return baseMapper.dictItemsByDictCode(dictCode);
    }
}
