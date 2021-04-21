package cn.tinybee.ke.portal.service;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodVodService;
import cn.tinybee.ke.biz.material.service.IMaterialFileService;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseService;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/29 12:51
 */
@Service
public class VodApiService {

    @Autowired
    private IUmsStudentCourseService iUmsStudentCourseService;

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Autowired
    private ICmsPeriodVodService iCmsPeriodVodService;

    @Autowired
    private IMaterialFileService materialFileService;

    @Transactional
    public VodPlayAuthResult getVodAuth(Long vodId, Long courseId, Long periodId, Long currentUserId, boolean auth) {
        // 查看此视频是否已购买
        UmsStudentCourse umsStudentCourse = iUmsStudentCourseService.memberContent(courseId, currentUserId);
        if (umsStudentCourse == null) {
            CmsCourse cmsCourse = iCmsCourseService.getById(courseId);
            if (cmsCourse.getFree()) {
                // 若免费
                iUmsStudentCourseService.join(courseId, currentUserId, false);
            } else {
                throw new BusinessException("无权限,请先购买");
            }
        }
        VodPlayAuthResult vodPlayAuth = materialFileService.getVodPlayAuth(vodId, auth);
        return vodPlayAuth;
    }
}
