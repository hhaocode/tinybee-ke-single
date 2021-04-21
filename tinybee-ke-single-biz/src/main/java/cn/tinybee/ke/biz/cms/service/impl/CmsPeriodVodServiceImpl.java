package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsPeriodVod;
import cn.tinybee.ke.biz.cms.mapper.CmsPeriodVodMapper;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodVodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-26
 */
@Service
public class CmsPeriodVodServiceImpl extends ServiceImpl<CmsPeriodVodMapper, CmsPeriodVod> implements ICmsPeriodVodService {

    @Override
    public List<CmsPeriodVod> listVods(Long periodId) {
        return baseMapper.listVods(periodId);
    }

    @Override
    public List<CmsPeriodVod> listVodsByCourseId(Long courseId) {
        return baseMapper.listVodsByCourseId(courseId);
    }
}
