package cn.tinybee.ke.biz.pm.handle;

import cn.tinybee.ke.biz.pm.entity.PmConfiguration;
import cn.tinybee.ke.common.entity.Operator;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/18 15:55
 */
public interface ConfigurationHandle {

    void execute(Operator user, PmConfiguration configuration);

}
