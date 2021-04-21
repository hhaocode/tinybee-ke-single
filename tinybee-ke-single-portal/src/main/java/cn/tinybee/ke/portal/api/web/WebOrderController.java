package cn.tinybee.ke.portal.api.web;

import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.sem.dto.OrderPaymentDto;
import cn.tinybee.ke.biz.sem.dto.OrderSubmitDto;
import cn.tinybee.ke.biz.sem.entity.SemOrder;
import cn.tinybee.ke.biz.sem.entity.SemPayment;
import cn.tinybee.ke.biz.sem.service.ISemOrderService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.exception.OrderPaidException;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Classname WebOrderController
 * @Description TODO
 * @Date 2020/6/17 15:20
 * @Created by hao.huang
 */
@RestController
@RequestMapping("/api/order")
public class WebOrderController extends BaseController {

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Autowired
    private ISemOrderService iSemOrderService;

    /**
     *
     * @param courseId
     * @return
     */
    @GetMapping("/compute/{courseId}")
    public ApiResult<List<CmsCourseDto>> compute(@PathVariable Long courseId) {
        ApiResult<List<CmsCourseDto>> success = ApiResult.success(Arrays.asList(iCmsCourseService.get(courseId)));
        success.setUuid(UUID.randomUUID().toString());
        return success;
    }

    @PostMapping("/submit")
    public ApiResult<SemOrder> submit (@RequestBody OrderSubmitDto orderSubmitDto) {
        try {
            SemOrder submit = iSemOrderService.submit(orderSubmitDto, this.getCurrentUserId());
            return ApiResult.success(submit);
        }catch (OrderPaidException e) {
            ApiResult<SemOrder> failed = ApiResult.failed(e.getMessage());
            failed.setCode(ApiResult.ORDER_PAID_CODE);
            return failed;
        }
    }

    @GetMapping("/getForPay/{orderNo}")
    public ApiResult<SemOrder> getForPay (@PathVariable String orderNo) {
        ApiResult<SemOrder> success = ApiResult.success(iSemOrderService.getForPay(orderNo, this.getCurrentUserId()));
        success.setUuid(UUID.randomUUID().toString());
        return success;
    }

    @PostMapping("/pay")
    public ApiResult<Object> pay (@RequestBody OrderPaymentDto orderPayment) {
        SemPayment pay = iSemOrderService.pay(orderPayment, this.getCurrentUserId());
        return ApiResult.success(pay);
    }


    @GetMapping("/page/user")
    public ApiResult<Page<SemOrder>> pageUser (PageParam pageParam) {
        return ApiResult.success(iSemOrderService.pageUser(this.pageConvert(pageParam), this.getCurrentUserId()));
    }
}
