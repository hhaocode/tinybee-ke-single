package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsPeriodVod;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-26
 */
public interface ICmsPeriodVodService extends IService<CmsPeriodVod> {

    List<CmsPeriodVod> listVods(Long periodId);

    List<CmsPeriodVod> listVodsByCourseId(Long courseId);

}
