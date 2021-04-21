package cn.tinybee.ke.common.aspect;

import cn.tinybee.ke.common.annotation.DataScope;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 *
 * @author huanghao
 * @version 1.0
 * @description 数据权限过滤
 * @date 2020/8/31 9:45
 */
@Aspect
@Component
public class DataScopeAspect {

    // 用于管理/运营
    // 全部数据权限 机构/部门 机构/部门及以下数据权限 本操作人数据权限  自定数据权限


    // 织入点
    @Pointcut("@annotation(cn.tinybee.ke.common.annotation.DataScope)")
    public void pointCut () {}

    @Before("pointCut()")
    public void before (JoinPoint point) {
        handleDataScope(point);
    }

    private void handleDataScope(JoinPoint point) {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method == null) {
            return;
        }
        DataScope dataScope = method.getAnnotation(DataScope.class);

        DataScope.DataScopeMark[] value = dataScope.value();

        dataScopeFilter(point, value);
    }

    private void dataScopeFilter(JoinPoint point, DataScope.DataScopeMark[] dataScopes) {
        // 获取当前用户
        // 将参数放进对应的参数中
        //
    }

//    dataS
}
