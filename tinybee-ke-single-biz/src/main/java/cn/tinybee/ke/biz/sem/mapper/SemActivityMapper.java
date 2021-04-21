package cn.tinybee.ke.biz.sem.mapper;

import cn.tinybee.ke.biz.sem.entity.SemActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
public interface SemActivityMapper extends BaseMapper<SemActivity> {

    List<SemActivity> listActivityNoPage(@Param("activityIds")  Collection<Long> activityIds);
}
