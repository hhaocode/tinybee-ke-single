package cn.tinybee.ke.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam implements Serializable {

    private static final long serialVersionUID = 4850844388724821228L;
    private Integer size;
    private Integer page;
}
