package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsLearningPathCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
public interface ICmsLearningPathCourseService extends IService<CmsLearningPathCourse> {

    List<CmsLearningPathCourse> listPathCourseByPathId(Long pathId);

    int countCourseByPathId(Long pathId);

}
