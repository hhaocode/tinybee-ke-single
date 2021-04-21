package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsLearningPathStage;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 学习路径阶段 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
public interface ICmsLearningPathStageService extends IService<CmsLearningPathStage> {

    List<CmsLearningPathStage> listStageByPathId(Long pathId);

    int countStageByPathId (Long pathId);

    boolean saveOrModify(Operator operator, CmsLearningPathStage param);

    boolean deleteById(Operator operator, Long id);
}
