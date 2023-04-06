package com.lzj.springbootexamples.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author Created by luzhenjiang
 * @date 2023/4/6 17:55
 * @description
 */
@Component
public class CustomizeBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    /**
     * 可以注册、移除、获取BeanDefinition，以及修改BeanDefinition等
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //通过BeanDefinition修改属性值
        BeanDefinition userBeanDefinition = registry.getBeanDefinition("user");
        userBeanDefinition.getPropertyValues().addPropertyValue("name", " init name value");
        System.out.println("postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition userBeanDefinition = beanFactory.getBeanDefinition("user");
        if (Objects.isNull(userBeanDefinition)) {
            return;
        }
        //修改初始化方法
        userBeanDefinition.setInitMethodName("otherInit");
        System.out.println("postProcessBeanFactory");
    }
}
