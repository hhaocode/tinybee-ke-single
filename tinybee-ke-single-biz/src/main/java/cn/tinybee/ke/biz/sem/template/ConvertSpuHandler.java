package cn.tinybee.ke.biz.sem.template;

import cn.tinybee.ke.biz.sem.entity.SemSpu;
import cn.tinybee.ke.common.entity.Operator;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/15 16:11
 */
public interface ConvertSpuHandler<T> {

    SemSpu convert(Operator operator, T data);

}
