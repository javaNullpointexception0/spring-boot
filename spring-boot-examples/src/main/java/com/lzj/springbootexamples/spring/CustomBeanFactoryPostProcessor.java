package com.lzj.springbootexamples.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        StringBuffer sb = new StringBuffer("操作前的BeanDefinition：");
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            sb.append("propertyName=").append(propertyValue.getName()).append(";propertyValue=").append(propertyValue.getValue()).append(";");
        }
        System.out.println(sb.toString());
        //属性值设置默认值
        propertyValues.add("name", "lzj");
        //增加属性，由于address不在User中定义，所以这里是无法增加这样的说行的
        //propertyValues.add("address", "gz");
        //修改Bean的默认值
        propertyValues.add("gender", "female");

        sb = new StringBuffer("操作后的BeanDefinition：");
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            sb.append("propertyName=").append(propertyValue.getName()).append(",propertyValue=").append(propertyValue.getValue()).append(";");
        }
        System.out.println(sb.toString());
    }
}
