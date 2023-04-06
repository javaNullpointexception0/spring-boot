package com.lzj.springbootexamples.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Created by luzhenjiang
 * @date 2023/4/6 14:08
 * @description
 */
@Component
public class CustomizeInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private static final String PACKAGE_PREFIX = "com.lzj.springbootexamples.entity.User";

    /**
     * 实例化之前被调用
     * @param beanClass
     * @param beanName
     * @return Object
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass.getName().startsWith(PACKAGE_PREFIX)) {
                System.out.println("postProcessBeforeInstantiation：" + beanClass.getName());
        }
        return null;
    }

    /**
     * 实例化之后被调用
     * @param bean
     * @param beanName
     * @return boolean
     * @throws BeansException
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getName().startsWith(PACKAGE_PREFIX)) {
            System.out.println("postProcessAfterInstantiation：" + bean.getClass().getName());
        }
        return true;
    }

    /**
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return PropertyValues
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (bean.getClass().getName().startsWith(PACKAGE_PREFIX)) {
            System.out.println("postProcessProperties：" + bean.getClass().getName());
        }
        return null;
    }

    /**
     * 执行初始化方法之前被调用
     * @param bean
     * @param beanName
     * @return Object
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getName().startsWith(PACKAGE_PREFIX)) {
            System.out.println("postProcessBeforeInitialization：" + bean.getClass().getName());
        }
        return null;
    }

    /**
     * 执行初始化方法之后被调用
     * @param bean
     * @param beanName
     * @return Object
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getName().startsWith(PACKAGE_PREFIX)) {
            System.out.println("postProcessAfterInitialization：" + bean.getClass().getName());
        }
        return null;
    }
}
