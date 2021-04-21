package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsCourseClassify;
import cn.tinybee.ke.biz.cms.mapper.CmsCourseClassifyMapper;
import cn.tinybee.ke.biz.cms.service.ICmsCourseClassifyService;
import cn.tinybee.ke.common.entity.IdNameType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-01
 */
@Service
public class CmsCourseClassifyServiceImpl extends ServiceImpl<CmsCourseClassifyMapper, CmsCourseClassify> implements ICmsCourseClassifyService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRelation(Long courseId, List<Long> classifyIds, Integer type) {
        QueryWrapper<CmsCourseClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("type", type);
        Map<Long, CmsCourseClassify> dbMap = this.list(queryWrapper).stream().collect(Collectors.toMap(CmsCourseClassify::getClassifyId, Function.identity()));
        // 删除
        if (classifyIds != null) {
            for (Long classifyId : classifyIds) {
                CmsCourseClassify remove = dbMap.remove(classifyId);
                if (remove == null) {
                    CmsCourseClassify cmsCourseClassify = new CmsCourseClassify();
                    cmsCourseClassify.setCourseId(courseId);
                    cmsCourseClassify.setClassifyId(classifyId);
                    cmsCourseClassify.setType(type);
                    this.save(cmsCourseClassify);
                }
            }
        }
        Set<Long> longs = dbMap.keySet();
        if (!longs.isEmpty()) {
            QueryWrapper<CmsCourseClassify> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("course_id", courseId);
            deleteWrapper.eq("type", type);
            deleteWrapper.in("classify_id", longs);
            this.remove(deleteWrapper);
        }
        return true;
    }

    @Override
    public Map<Integer, List<CmsCourseClassify>> getRelation(Long courseId) {
        QueryWrapper<CmsCourseClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        return this.list(queryWrapper).stream().collect(Collectors.groupingBy(CmsCourseClassify::getType));
    }

    @Override
    public Map<Integer, List<Long>> getRelationIds(Long courseId) {
        Map<Integer, List<CmsCourseClassify>> relation = this.getRelation(courseId);
        Map<Integer, List<Long>> res = new HashMap<>();
        for (Map.Entry<Integer, List<CmsCourseClassify>> integerListEntry : relation.entrySet()) {
            res.put(integerListEntry.getKey(), integerListEntry.getValue().stream().map(v -> v.getClassifyId()).collect(Collectors.toList()));
        }
        return res;
    }

}
