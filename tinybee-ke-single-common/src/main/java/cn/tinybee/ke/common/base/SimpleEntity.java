package cn.tinybee.ke.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/15 13:36
 */
public class SimpleEntity<ID> implements Serializable {
    private static final long serialVersionUID = -7643888797274692752L;


    @TableId(type = IdType.AUTO)
    protected ID id;
}
