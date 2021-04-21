package cn.tinybee.ke.biz.cms.mapper;

import cn.tinybee.ke.biz.cms.entity.CmsPeriodFile;
import cn.tinybee.ke.biz.material.entity.MaterialFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2021-04-18
 */
public interface CmsPeriodFileMapper extends BaseMapper<CmsPeriodFile> {

    List<MaterialFile> listFileByPeriodId(@Param("periodId") Long periodId);
}
