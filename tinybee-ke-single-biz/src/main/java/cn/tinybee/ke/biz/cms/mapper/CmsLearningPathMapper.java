package cn.tinybee.ke.biz.cms.mapper;

import cn.tinybee.ke.biz.cms.entity.CmsLearningPath;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 学习路径 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
public interface CmsLearningPathMapper extends BaseMapper<CmsLearningPath> {

    List<CmsLearningPath> listPath(@Param("kw") String kw);

}
