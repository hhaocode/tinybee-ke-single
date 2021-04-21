package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.dto.CmsPeriodDto;
import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内容文章表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
public interface ICmsPeriodService extends IService<CmsPeriod> {

    Map<Long, List<CmsPeriodDto>> mapItemByContentId(Long contentId);

    CmsPeriod saveOrModify(CmsPeriod cmsPeriod, Long currentUserId);

    CmsPeriod getPeriodById(Long id);

    List<CmsPeriod> periodsByCourseId(Long courseId);

    boolean doUpdate(Operator user, Long id);
}
