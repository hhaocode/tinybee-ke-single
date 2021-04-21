package cn.tinybee.ke.common.entity;

import lombok.Data;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/1 3:26
 */
@Data
public class IdNameType<ID, NAME> {

    private ID id;

    private NAME name;
}
