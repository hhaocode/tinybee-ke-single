package cn.tinybee.ke.biz.cms.mapper;

import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 内容文章表 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
public interface CmsPeriodMapper extends BaseMapper<CmsPeriod> {

    CmsPeriod getDetailById (@Param("id") Long id);

}
