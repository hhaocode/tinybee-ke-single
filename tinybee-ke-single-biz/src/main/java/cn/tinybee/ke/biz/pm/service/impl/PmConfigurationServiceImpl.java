package cn.tinybee.ke.biz.pm.service.impl;

import cn.tinybee.ke.biz.pm.entity.PmConfiguration;
import cn.tinybee.ke.biz.pm.handle.ConfigurationHandle;
import cn.tinybee.ke.biz.pm.mapper.PmConfigurationMapper;
import cn.tinybee.ke.biz.pm.service.IPmConfigurationService;
import cn.tinybee.ke.common.annotation.OnlySuperAdminOperate;
import cn.tinybee.ke.common.context.SpringContext;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
@Service
public class PmConfigurationServiceImpl extends ServiceImpl<PmConfigurationMapper, PmConfiguration> implements IPmConfigurationService {

    @OnlySuperAdminOperate
    @Override
    public boolean modify(Operator operator, PmConfiguration param) {
        AssertUtils.notNull(param.getId(), "无效的配置");
        PmConfiguration dbConfig = this.getById(param.getId());
        AssertUtils.notNull(dbConfig, "无效的配置");
        dbConfig.setCurrentValue(param.getCurrentValue());
        dbConfig.setModifier(operator.getId());
        dbConfig.setModifyTime(LocalDateTime.now());
        this.updateById(dbConfig);
        if (param.isImmediatelyTriggered()) {
            // 触发
            if (StringUtils.isNotBlank(param.getHandlerName())) {
                // 执行
                ConfigurationHandle handler = (ConfigurationHandle) SpringContext.getBean(param.getHandlerName());
                handler.execute(operator, dbConfig);
                dbConfig.setExecuteTime(LocalDateTime.now());
                this.updateById(dbConfig);
            }
        }
        return true;
    }

    @Override
    public PmConfiguration getByType(String type) {
        QueryWrapper<PmConfiguration> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_type", type);
        return this.getOne(queryWrapper);
    }
}
