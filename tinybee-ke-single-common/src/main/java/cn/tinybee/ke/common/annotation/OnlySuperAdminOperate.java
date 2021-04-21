package cn.tinybee.ke.common.annotation;

import java.lang.annotation.*;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/9 14:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnlySuperAdminOperate {
}
