package cn.tinybee.ke.biz.pm.service;

import cn.tinybee.ke.biz.pm.entity.PmConfiguration;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
public interface IPmConfigurationService extends IService<PmConfiguration> {

    boolean modify(Operator operator, PmConfiguration param);

    PmConfiguration getByType (String type);

}
