package cn.tinybee.ke.biz.material.mapper;

import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.common.entity.GroupCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
public interface MaterialFileMapper extends BaseMapper<MaterialFile> {

    List<GroupCount> groupingStatisticsCnt(@Param("type") Integer type);

}
