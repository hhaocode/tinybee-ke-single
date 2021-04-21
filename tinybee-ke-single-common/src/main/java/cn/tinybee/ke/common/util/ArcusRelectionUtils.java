package cn.tinybee.ke.common.util;

import org.dozer.util.ReflectionUtils;

/**
 * @Classname RelectionUtils
 * @Description TODO
 * @Date 2020/5/29 9:10
 * @Created by hao.huang
 */
public class ArcusRelectionUtils {

    public static <T> T invoke(String methodName, Object o, Object[] args) {
        return (T) ReflectionUtils.invoke(ReflectionUtils.getMethod(o, methodName), o, args);
    }

}
