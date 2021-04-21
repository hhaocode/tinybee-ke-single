package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsLearningPath;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学习路径 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
public interface ICmsLearningPathService extends IService<CmsLearningPath> {

    CmsLearningPath saveOrModify(Operator operator, CmsLearningPath param);

    CmsLearningPath getDetailById(Long id);

    Boolean deleteById(Long id);

    List<CmsLearningPath> listPath(String kw);
}
