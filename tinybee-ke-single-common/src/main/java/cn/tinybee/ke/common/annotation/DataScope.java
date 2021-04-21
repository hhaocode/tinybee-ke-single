package cn.tinybee.ke.common.annotation;

import java.lang.annotation.*;

/**
 * @author huanghao
 * @version 1.0
 * @description 数据权限控制
 * @date 2020/8/31 9:48
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {


    DataScopeMark[] value() default {};

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    @interface DataScopeMark {
//        DataScopeMark type() default  DataScopeMark.A
        String key() default "";

        String alias() default  "";

    }
}


