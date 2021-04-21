package cn.tinybee.ke.portal.api.common;

import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import cn.tinybee.ke.portal.service.VodApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/29 12:46
 */
@RestController
@RequestMapping("/api/vod")
public class VodApiController extends BaseController {

//    private ICmsPeriodVodService iCmsPeriodVodService;

    @Autowired
    private VodApiService vodApiService;

    @GetMapping("/getVodAuth/{vodId}")
    public ApiResult<VodPlayAuthResult> getVodAuth (@PathVariable Long vodId,
                                                    @RequestParam Long courseId,
                                                    @RequestParam Long periodId,
                                                    @RequestParam(required = false, defaultValue = "true" ) boolean auth
                                 ) {
        return ApiResult.success(vodApiService.getVodAuth(vodId, courseId, periodId, this.getCurrentUserId(), auth));
    }
}
