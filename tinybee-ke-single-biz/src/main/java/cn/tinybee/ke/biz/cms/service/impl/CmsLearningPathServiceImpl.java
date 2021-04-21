package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsLearningPath;
import cn.tinybee.ke.biz.cms.entity.CmsLearningPathStage;
import cn.tinybee.ke.biz.cms.mapper.CmsLearningPathMapper;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathCourseService;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathService;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathStageService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 学习路径 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@Service
public class CmsLearningPathServiceImpl extends ServiceImpl<CmsLearningPathMapper, CmsLearningPath> implements ICmsLearningPathService {

    private ICmsLearningPathStageService cmsLearningPathStageService;

    private ICmsLearningPathCourseService cmsLearningPathCourseService;

    @Autowired
    public void setCmsLearningPathStageService(ICmsLearningPathStageService cmsLearningPathStageService) {
        this.cmsLearningPathStageService = cmsLearningPathStageService;
    }

    @Autowired
    public void setCmsLearningPathCourseService(ICmsLearningPathCourseService cmsLearningPathCourseService) {
        this.cmsLearningPathCourseService = cmsLearningPathCourseService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CmsLearningPath saveOrModify(Operator operator, CmsLearningPath param) {
        if (param.getId() == null) {
            //新建
        } else {
            // 更新
        }

        // 获取时长  TODO 或者不需要存储 查询的时候 直接计算
        this.saveOrUpdate(param);
        List<CmsLearningPathStage> stages = param.getStages();
        saveStages(operator, param);
        return param;
    }

    private void saveStages (Operator operator, CmsLearningPath param) {
        List<CmsLearningPathStage> stages = param.getStages();
        if (CollectionUtils.isEmpty(stages)) {
            return;
        }
        for (CmsLearningPathStage stage : stages) {
            stage.setPathId(param.getId());
            cmsLearningPathStageService.saveOrModify(operator, stage);
        }
    }


    @Override
    public CmsLearningPath getDetailById(Long id) {
        CmsLearningPath path = this.getById(id);
        AssertUtils.notNull(path, "学习路径不存在");
        // 计算
        List<CmsLearningPathStage> stageList =  cmsLearningPathStageService.listStageByPathId (id);
        path.setStages(stageList);
        return path;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean deleteById(Long id) {
        int sCount = cmsLearningPathStageService.countStageByPathId(id);

        int cCount = cmsLearningPathCourseService.countCourseByPathId(id);

        AssertUtils.isFalse( sCount > 0 || cCount > 0, "请先清除学习路径下的信息");

        return this.deleteById(id);
    }

    @Override
    public List<CmsLearningPath> listPath(String kw) {
        List<CmsLearningPath> res = baseMapper.listPath(kw);
        return res;
    }

}
