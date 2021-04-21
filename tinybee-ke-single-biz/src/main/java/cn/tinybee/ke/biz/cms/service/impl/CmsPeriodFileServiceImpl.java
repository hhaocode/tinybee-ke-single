package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.entity.CmsPeriodFile;
import cn.tinybee.ke.biz.cms.mapper.CmsPeriodFileMapper;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodFileService;
import cn.tinybee.ke.biz.material.entity.MaterialFile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-04-18
 */
@Service
public class CmsPeriodFileServiceImpl extends ServiceImpl<CmsPeriodFileMapper, CmsPeriodFile> implements ICmsPeriodFileService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void handleSave(CmsPeriod cmsPeriod, List<MaterialFile> files) {
        Long periodId = cmsPeriod.getId();
        QueryWrapper<CmsPeriodFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("period_id", periodId);
//        Map<Long, CmsPeriodFile> collect = this.list(queryWrapper).stream().collect(Collectors.toMap(CmsPeriodFile::getFileId, Function.identity()));
//          TODO 先简单实现
        this.remove(queryWrapper);
        if (files != null) {
            for (MaterialFile file : files) {
                CmsPeriodFile periodFile = new CmsPeriodFile();
                periodFile.setFileId(file.getId());
                periodFile.setPeriodId(periodId);
                this.save(periodFile);
            }
        }
    }

    @Override
    public List<MaterialFile> listFileByPeriodId(Long periodId) {
        return baseMapper.listFileByPeriodId(periodId);
    }
}
