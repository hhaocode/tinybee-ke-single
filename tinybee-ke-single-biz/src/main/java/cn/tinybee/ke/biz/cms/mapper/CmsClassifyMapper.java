package cn.tinybee.ke.biz.cms.mapper;

import cn.tinybee.ke.biz.cms.entity.CmsClassify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 内容分类 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
public interface CmsClassifyMapper extends BaseMapper<CmsClassify> {

    List<CmsClassify> listByCourseIds(@Param("courseIds") Collection<Long> courseIds);
}
