package cn.tinybee.ke.common.aspect;

import cn.tinybee.ke.common.annotation.OnlySuperAdminOperate;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.exception.BusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/9 14:41
 */
@Aspect
@Component
public class OnlySuperAdminOperateAspect {


    @Pointcut("@annotation(cn.tinybee.ke.common.annotation.OnlySuperAdminOperate)")
    public void pointcut () {}

    @Before("pointcut()")
    public void before (JoinPoint point) {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method == null) {
            return;
        }
        OnlySuperAdminOperate annotation = method.getAnnotation(OnlySuperAdminOperate.class);
        if (annotation != null) {
            Object[] args = point.getArgs();
            for (Object arg : args) {
                if (arg instanceof Operator) {
                    Operator currentArg = (Operator) arg;
                    if (currentArg == null || !currentArg.isSuperAdmin()) {
                        throw new BusinessException("只能超级管理员才能操作");
                    }
                }
            }

        }
    }
}
