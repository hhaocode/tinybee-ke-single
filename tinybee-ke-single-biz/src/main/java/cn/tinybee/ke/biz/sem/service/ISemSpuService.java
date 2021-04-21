package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.entity.SemSku;
import cn.tinybee.ke.biz.sem.entity.SemSpu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
public interface ISemSpuService extends IService<SemSpu> {


    SemSpu saveOrModify (SemSku param);

    SemSpu getByTypeAndReferenceId(int type, Long referenceId);

}
