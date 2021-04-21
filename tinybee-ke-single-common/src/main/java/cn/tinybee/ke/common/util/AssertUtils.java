package cn.tinybee.ke.common.util;

import cn.tinybee.ke.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * @Classname AssertUtils
 * @Description TODO
 * @Date 2020/6/8 13:01
 * @Created by hao.huang
 */
public class AssertUtils {

    public static void notNull(Object target, String message) {
        if (target == null) {
            throw new BusinessException(message);
        }
    }
    public static void isNull (Object target, String message) {
        if (target != null) {
            throw new BusinessException(message);
        }
    }

    public static void isTrue(Boolean target, String message) {
        if (!target) {
            throw new BusinessException(message);
        }
    }

    public static void isFalse(Boolean target, String message) {
        if (target) {
            throw new BusinessException(message);
        }
    }

    public static void isEq(Object src, Object target, String message) {
        if (src != null && !src.equals(target)) {
            throw new BusinessException(message);
        } else if (src == null && target != null) {
            throw new BusinessException(message);
        }
    }

    public static void notEmpty(Collection<Object> targetCollection, String message) {
        if (targetCollection.isEmpty()) {
            throw new BusinessException(message);
        }
    }

    public static void isEmpty (Collection<Object> targetCollection, String message) {
        if (!targetCollection.isEmpty()) {
            throw new BusinessException(message);
        }
    }

    public static void notBlank (String target, String message) {
        if (StringUtils.isBlank(target)) {
            throw new BusinessException(message);
        }
    }

    public static void isBlank (String target, String message) {
        if (StringUtils.isNotBlank(target)) {
            throw new BusinessException(message);
        }
    }
}
