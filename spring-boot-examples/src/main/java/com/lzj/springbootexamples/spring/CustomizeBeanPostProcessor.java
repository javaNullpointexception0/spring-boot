package com.lzj.springbootexamples.spring;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
/**
 * @author Created by luzhenjiang
 * @date 2021/5/28 14:09
 * @description Bean初始化方法调用前的前后置处理器
 */
@Component
public class CustomizeBeanPostProcessor implements BeanPostProcessor {

    private static final String PACKAGE_PREFIX = "com.lzj";
    /**
     * Bean初始化方法调用前的扩展点，在Bean初始化方法调用前会执行该方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().getName().startsWith(PACKAGE_PREFIX)) {
            return bean;
        }
        System.out.println("Bean[" + bean.getClass().getName() + "]初始化方法执行前的处理逻辑...");
        /*if (bean instanceof CustomConfiguration) {
            Integer serverPort = ((CustomConfiguration)bean).serverPort;
            SpringUtil springUtil = ((CustomConfiguration)bean).SpringUtil;
            System.out.println("@Value配置值：" + serverPort + ";autowired注入：" + springUtil);
        }*/
        return bean;
    }

    /**
     * Bean初始化方法调用后的扩展点，在Bean初始化方法调用后会执行该方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().getName().startsWith(PACKAGE_PREFIX)) {
            return bean;
        }
        System.out.println("Bean[" + bean.getClass().getName() + "]初始化方法执行后的处理逻辑...");
        return bean;
    }
}
