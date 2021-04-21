package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.entity.SemOrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
public interface ISemOrderItemService extends IService<SemOrderItem> {

    List<SemOrderItem> listOrderItemBySkuIds (Collection<Long> skuIds);

    List<SemOrderItem> listOrderItemByOrderIds(Collection<Long> orderIds);

}
