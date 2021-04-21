package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.entity.CmsPeriodFile;
import cn.tinybee.ke.biz.material.entity.MaterialFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-04-18
 */
public interface ICmsPeriodFileService extends IService<CmsPeriodFile> {

    void handleSave(CmsPeriod cmsPeriod, List<MaterialFile> files);

    List<MaterialFile> listFileByPeriodId (Long periodId);

}
