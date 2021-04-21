package cn.tinybee.ke.biz.pm.handle.impl;

import cn.tinybee.ke.biz.pm.entity.PmConfiguration;
import cn.tinybee.ke.biz.pm.handle.ConfigurationHandle;
import cn.tinybee.ke.common.annotation.OnlySuperAdminOperate;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/18 15:57
 */
@Component("ClassifyLevelHandler")
public class ClassifyLevelHandle implements ConfigurationHandle {

    @Autowired
    private RedisService redisService;

    @OnlySuperAdminOperate
    @Override
    public void execute(Operator user, PmConfiguration configuration) {
        // 更新所有的数据到一级


        // 更新缓存

        //
    }
}
