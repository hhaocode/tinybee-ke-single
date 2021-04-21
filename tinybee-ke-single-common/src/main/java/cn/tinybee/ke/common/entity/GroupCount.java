package cn.tinybee.ke.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/10/23 15:32
 */
@Data
public class GroupCount implements Serializable {

    private Long id;
    private String name;
    private Long cnt;
}
