package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.entity.SemActivity;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
public interface ISemActivityService extends IService<SemActivity> {

    boolean saveOrModify(Operator operator, SemActivity param);


    Page<SemActivity> pageRecords(Operator operator, Page page);

    SemActivity get(Long id);

    boolean delete(Operator operator, Long id);

}
