package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsCourseClassify;
import cn.tinybee.ke.common.entity.IdNameType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-01
 */
public interface ICmsCourseClassifyService extends IService<CmsCourseClassify> {

    boolean saveRelation(Long courseId, List<Long> classifyIds, Integer type);

    Map<Integer, List<CmsCourseClassify>> getRelation(Long courseId);

    Map<Integer, List<Long>> getRelationIds(Long courseId);

}
