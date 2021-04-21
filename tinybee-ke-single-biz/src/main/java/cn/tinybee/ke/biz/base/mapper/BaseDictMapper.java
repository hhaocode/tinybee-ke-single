package cn.tinybee.ke.biz.base.mapper;

import cn.tinybee.ke.biz.base.entity.BaseDict;
import cn.tinybee.ke.biz.base.entity.BaseDictItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
public interface BaseDictMapper extends BaseMapper<BaseDict> {


    List<BaseDictItem> dictItemsByDictCode(@Param("dictCode") String dicCode);
}
