package cn.tinybee.ke.biz.cms.mapper;

import cn.tinybee.ke.biz.cms.entity.CmsPeriodVod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-26
 */
public interface CmsPeriodVodMapper extends BaseMapper<CmsPeriodVod> {

    @Select("select t.*, v.type AS vodType, v.`name` as vodName, v.title AS vodTitle " +
            "from t_cms_period_vod t LEFT JOIN t_material_vod v ON t.available = true AND t.vod_id = v.id WHERE t.period_id = #{periodId}")
    List<CmsPeriodVod> listVods(@Param("periodId") Long periodId);

    List<CmsPeriodVod> listVodsByCourseId(@Param("courseId") Long courseId);
}
