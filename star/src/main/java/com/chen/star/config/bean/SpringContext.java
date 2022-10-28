package com.chen.star.config.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware
{
    private static ApplicationContext APPLICATION_CONTEXT;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        APPLICATION_CONTEXT = applicationContext;
    }
    
    /**
     * 获取容器
     *
     * @return
     */
    public static ApplicationContext getContext()
    {
        return APPLICATION_CONTEXT;
    }
    
    /**
     * 获取容器对象
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type)
    {
        return APPLICATION_CONTEXT.getBean(type);
    }
    
    /**
     * 获取容器对象
     *
     * @param className
     * @param <T>
     * @return
     */
    public static <T> T getBean(String className)
    {
        return (T) APPLICATION_CONTEXT.getBean(className);
    }
    
    /**
     * 主动向Spring容器中注册bean
     *
     * @param name  BeanName
     * @param clazz 注册的bean的类性
     * @param args  构造方法的必要参数，顺序和类型要求和clazz中定义的一致
     * @param <T>
     * @return 返回注册到容器中的bean对象
     */
    public static <T> T registerBean(String name, Class<T> clazz, Object ... args)
    {
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) getContext();
        if (applicationContext.containsBean(name))
        {// 判断待注册的BEAN是否已存在
            Object bean = applicationContext.getBean(name);
            if (bean.getClass().isAssignableFrom(clazz))
            {
                return (T) bean;
            }
            else
            {
                throw new RuntimeException("BeanName 重复 " + name);
            }
        }
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        for (Object arg : args)
        {
            beanDefinitionBuilder.addConstructorArgValue(arg);
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) applicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition(name, beanDefinition);
        return applicationContext.getBean(name, clazz);
    }
}
