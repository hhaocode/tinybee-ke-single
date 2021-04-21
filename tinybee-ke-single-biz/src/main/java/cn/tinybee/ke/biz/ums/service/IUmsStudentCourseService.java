package cn.tinybee.ke.biz.ums.service;

import cn.tinybee.ke.biz.ums.dto.UserCourseDetailDto;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员拥有内容表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
public interface IUmsStudentCourseService extends IService<UmsStudentCourse> {

    Boolean join(Long contentId, Long memberId, Boolean paid);

    UmsStudentCourse memberContent(Long contentId, Long memberId);

    Page<UserCourseDetailDto> memberCoursePage(Page page, Long currentUserId);

    int countUserCourse (Long userId);
}

