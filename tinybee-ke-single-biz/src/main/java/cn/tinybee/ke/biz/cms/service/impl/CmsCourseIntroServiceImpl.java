package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsCourseIntro;
import cn.tinybee.ke.biz.cms.mapper.CmsCourseIntroMapper;
import cn.tinybee.ke.biz.cms.service.ICmsCourseIntroService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 内容介绍 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@Service
public class CmsCourseIntroServiceImpl extends ServiceImpl<CmsCourseIntroMapper, CmsCourseIntro> implements ICmsCourseIntroService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByCourseId(Long courseId) {
        QueryWrapper<CmsCourseIntro> introQueryWrapper = new QueryWrapper<>();
        introQueryWrapper.eq("course_id", courseId);
        return this.remove(introQueryWrapper);
    }
}
