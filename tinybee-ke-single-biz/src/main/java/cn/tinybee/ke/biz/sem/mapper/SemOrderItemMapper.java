package cn.tinybee.ke.biz.sem.mapper;

import cn.tinybee.ke.biz.sem.entity.SemOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
public interface SemOrderItemMapper extends BaseMapper<SemOrderItem> {


    //

    List<SemOrderItem> listOrderItemBySkuIds (@Param("skuIds") Collection<Long> skuIds);


    List<SemOrderItem> listOrderItemByOrderIds(@Param("orderIds") Collection<Long> orderIds);
}
