package cn.tinybee.ke.biz.sem.service.impl;

import cn.tinybee.ke.biz.sem.constant.SemConstants;
import cn.tinybee.ke.biz.sem.entity.SemSku;
import cn.tinybee.ke.biz.sem.entity.SemSpu;
import cn.tinybee.ke.biz.sem.mapper.SemSpuMapper;
import cn.tinybee.ke.biz.sem.service.ISemSpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
@Service
public class SemSpuServiceImpl extends ServiceImpl<SemSpuMapper, SemSpu> implements ISemSpuService {

    @Override
    public SemSpu saveOrModify(SemSku param) {
        return null;
    }

    @Override
    public SemSpu getByTypeAndReferenceId(int type, Long referenceId) {
        QueryWrapper<SemSpu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", SemConstants.COURSE_SPU_TYPE);
        queryWrapper.eq("reference_id", referenceId);
        return this.getOne(queryWrapper);
    }
}
