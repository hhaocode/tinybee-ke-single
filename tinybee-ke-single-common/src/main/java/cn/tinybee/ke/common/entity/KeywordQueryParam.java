package cn.tinybee.ke.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/28
 */
@Data
public class KeywordQueryParam implements Serializable {

    private String kw;
    private String[] ids;

}
