package cn.tinybee.ke.common.entity;

import lombok.Data;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/30
 */
@Data
public class IdIdsType<ID, IDS> {

    private ID id;
    private List<IDS> ids;
}
