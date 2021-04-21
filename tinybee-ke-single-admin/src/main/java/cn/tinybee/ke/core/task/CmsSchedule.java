package cn.tinybee.ke.core.task;

import cn.tinybee.ke.biz.material.service.IMaterialVodUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/22 17:17
 */
@Component
public class CmsSchedule {

    @Autowired
    private IMaterialVodUploadService iMaterialVodUploadService;

//    @Autowired
//    private IMaterialVodService iMaterialVodService;

    @Scheduled(cron = "* 0/5 * * * ?", fixedDelay=2000)
    public void vodInfoProcess () {
//        iMaterialVodService.list()
    }

}
