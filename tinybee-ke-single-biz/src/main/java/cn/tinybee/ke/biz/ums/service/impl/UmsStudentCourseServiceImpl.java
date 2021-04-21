package cn.tinybee.ke.biz.ums.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.ums.dto.UserCourseDetailDto;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import cn.tinybee.ke.biz.ums.mapper.UmsStudentCourseMapper;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseService;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 会员拥有内容表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
@Service
public class UmsStudentCourseServiceImpl extends ServiceImpl<UmsStudentCourseMapper, UmsStudentCourse> implements IUmsStudentCourseService {

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean join(Long courseId, Long memberId, Boolean paid) {
        AssertUtils.notNull(courseId, "无效的内容ID");
        AssertUtils.notNull(memberId, "无效的会员");
        CmsCourse content = iCmsCourseService.getById(courseId);
        if (content == null) {
            throw new BusinessException("无效的内容");
        }
        int count = this.count(memberContentQueryWrapper(courseId, memberId));
        if (count > 0) {
            return true;
        }
        UmsStudentCourse userCourse = new UmsStudentCourse();
        userCourse.setAvailable(true);
        userCourse.setCourseId(courseId);
        userCourse.setUserId(memberId);
        userCourse.setStudyHours(BigDecimal.ZERO);
        userCourse.setCreateTime(LocalDateTime.now());
        userCourse.setModifyTime(userCourse.getCreateTime());
        this.save(userCourse);
        iCmsCourseService.incrementApplyNum(courseId);
        return true;
    }

    @Override
    public UmsStudentCourse memberContent(Long contentId, Long memberId) {
        return this.getOne(memberContentQueryWrapper(contentId, memberId));
    }

    @Override
    public Page<UserCourseDetailDto> memberCoursePage(Page page, Long currentUserId) {
        return baseMapper.memberCoursePage(page, currentUserId);
    }

    @Override
    public int countUserCourse(Long userId) {
        QueryWrapper<UmsStudentCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("available", true);
        return this.count(queryWrapper);
    }

    private QueryWrapper<UmsStudentCourse> memberContentQueryWrapper(Long contentId, Long memberId) {
        QueryWrapper<UmsStudentCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", contentId);
        queryWrapper.eq("user_id", memberId);
        queryWrapper.eq("available", true);
        return queryWrapper;
    }

}
