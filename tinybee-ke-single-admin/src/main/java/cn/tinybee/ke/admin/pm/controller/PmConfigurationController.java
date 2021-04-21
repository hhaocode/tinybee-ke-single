package cn.tinybee.ke.admin.pm.controller;


import cn.tinybee.ke.biz.pm.entity.PmConfiguration;
import cn.tinybee.ke.biz.pm.service.IPmConfigurationService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/pm/t-pm-configuration")
public class PmConfigurationController extends BaseController {

    @Autowired
    private IPmConfigurationService iPmConfigurationService;


    @GetMapping("/map")
    public ApiResult<Map<String, PmConfiguration>> getConfiguration () {
        Map<String, PmConfiguration> configurationMap = iPmConfigurationService.list().stream().collect(Collectors.toMap(PmConfiguration::getConfigType, Function.identity()));
        return ApiResult.success(configurationMap);
    }

    @PutMapping("/modify")
    public ApiResult<Boolean> modify (@RequestBody PmConfiguration param) {
        return ApiResult.success(iPmConfigurationService.modify(this.getOperator(), param));
    }

}
