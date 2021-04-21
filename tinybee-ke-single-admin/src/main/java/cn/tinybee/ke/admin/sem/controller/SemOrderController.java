package cn.tinybee.ke.admin.sem.controller;


import cn.tinybee.ke.biz.sem.entity.SemOrder;
import cn.tinybee.ke.biz.sem.entity.SemOrderItem;
import cn.tinybee.ke.biz.sem.service.ISemOrderItemService;
import cn.tinybee.ke.biz.sem.service.ISemOrderService;
import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.biz.ums.service.IUmsStudentService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/api/sem/order")
public class SemOrderController extends BaseController {

    @Autowired
    private ISemOrderService semOrderService;

    @Autowired
    private ISemOrderItemService semOrderItemService;

    @Autowired
    private IUmsStudentService umsUserService;

    @GetMapping("/page")
    public ApiResult<Page<SemOrder>> page (PageParam pageParam) {
        QueryWrapper<SemOrder> queryWrapper = new QueryWrapper<>();
        Page<SemOrder> page = semOrderService.page(this.pageConvert(pageParam));
        List<SemOrder> records = page.getRecords();
        if (!records.isEmpty()) {
            Set<Long> orderIds = records.stream().map(v -> v.getId()).collect(Collectors.toSet());
            Map<Long, List<SemOrderItem>> longListMap = semOrderItemService.listOrderItemByOrderIds(orderIds).stream().collect(Collectors.groupingBy(SemOrderItem::getOrderId));
            Set<Long> userIds = records.stream().map(v -> v.getUserId()).collect(Collectors.toSet());
            Map<Long, UmsStudent> userIdMap = umsUserService.listByIds(userIds).stream().collect(Collectors.toMap(UmsStudent::getId, Function.identity()));
            records.forEach(v -> {
                v.setOrderItems(longListMap.get(v.getId()));
                v.setUser(userIdMap.get(v.getUserId()));
            });

        }
        return ApiResult.success(page);
    }
}
