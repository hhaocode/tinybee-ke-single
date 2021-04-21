package cn.tinybee.ke.biz.sem.template.handler;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.sem.constant.SemConstants;
import cn.tinybee.ke.biz.sem.entity.SemSpu;
import cn.tinybee.ke.biz.sem.service.ISemSkuService;
import cn.tinybee.ke.biz.sem.service.ISemSpuService;
import cn.tinybee.ke.biz.sem.template.ConvertSpuHandler;
import cn.tinybee.ke.common.entity.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/15 16:13
 */
@Component("courseConvertSpuHandler")
public class CourseConvertSpuHandler implements ConvertSpuHandler<CmsCourse> {

    private ISemSpuService semSpuService;

    private ISemSkuService semSkuService;

    @Autowired
    public void setSemSpuService(ISemSpuService semSpuService) {
        this.semSpuService = semSpuService;
    }

    @Autowired
    public void setSemSkuService(ISemSkuService semSkuService) {
        this.semSkuService = semSkuService;
    }

    @Override
    public SemSpu convert(Operator operator, CmsCourse data) {

        LocalDateTime now = LocalDateTime.now();
        // spu
        SemSpu spu = semSpuService.getByTypeAndReferenceId(SemConstants.COURSE_SPU_TYPE, data.getId());
        if (spu == null) {
            // 新建
            spu.setCreateTime(now);
        }

        //
        spu.setType(SemConstants.COURSE_SPU_TYPE);
        spu.setReferenceId(data.getId());
        spu.setTitle(data.getTitle());
        spu.setSubtitle(data.getSubtitle());
        spu.setSaleable(data.getStatus() == CmsCourse.ON_SHELF);
        spu.setValid(true);
        spu.setModifyTime(now);
        spu.setDeleted(false);
        // 构造SKU
        // 获取规格

        semSpuService.saveOrUpdate(spu);
        return spu;
    }
}
