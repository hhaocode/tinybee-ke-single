package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsCourseIntro;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 内容介绍 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
public interface ICmsCourseIntroService extends IService<CmsCourseIntro> {
    boolean deleteByCourseId(Long courseId);
}
