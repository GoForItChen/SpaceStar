package com.chen.mars.base.aop;

import com.chen.mars.config.database.DataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态数据源切入
 * <p>File: DataSourceAspect.java</p>
 * <p>Title: DataSourceAspect</p>
 * <p>Description: DataSourceAspect</p>
 * <p>Copyright: Copyright (c) 16/3/26</p>
 * <p>Company: Blocain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Aspect
@Order(-1)
@Component
public class DataSourceAspect
{
    /**
     * 仅切入到带有@SlaveDataSource注解的方法体中
     * 默认不带此注解的方法走主库；带有@SlaveDataSource的方法根据slave状态区分！
     * @param pjp
     * @throws Throwable
     */
    @Before("@annotation(com.chen.mars.base.aop.SlaveDataSource)")
    public void changeDataSource(JoinPoint pjp)
    {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        SlaveDataSource annotation = methodSignature.getMethod().getAnnotation(SlaveDataSource.class);
        if (null != annotation && annotation.slave())
        {
            DataSourceHolder.useSlave();
        }
    }
    
    /**
     * 还原数据源
     * @param point
     */
    @After("@annotation(com.chen.mars.base.aop.SlaveDataSource)")
    public void restoreDataSource(JoinPoint point)
    {
        DataSourceHolder.reset();
    }
}
