package cn.tinybee.ke.biz.base.service.impl;

import cn.tinybee.ke.biz.base.entity.BaseDictItem;
import cn.tinybee.ke.biz.base.mapper.BaseDictItemMapper;
import cn.tinybee.ke.biz.base.service.BaseDictItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典子项表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
@Service
public class BaseDictItemServiceImpl extends ServiceImpl<BaseDictItemMapper, BaseDictItem> implements BaseDictItemService {

    @Override
    public boolean saveBatch(List<BaseDictItem> list, Long dictId, Long currentUserId) {

        list.forEach(v -> {
            v.setDictId(dictId);
            if (v.getId() == null) {
                v.setCreateTime(LocalDateTime.now());
                v.setCreator(currentUserId);
            } else {
                v.setModifier(currentUserId);
                v.setModifyTime(LocalDateTime.now());
            }
            this.saveOrUpdate(v);
        });

        QueryWrapper<BaseDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_id", dictId);
        if (!list.isEmpty()) {
            queryWrapper.notIn("id", list.stream().map(v -> v.getId()).collect(Collectors.toSet()));
        }
        return this.remove(queryWrapper);
    }

    @Override
    public List<BaseDictItem> dictItems(Long dictId) {
        QueryWrapper<BaseDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_id", dictId);
        queryWrapper.eq("available", true);
        return this.list(queryWrapper);
    }

}
