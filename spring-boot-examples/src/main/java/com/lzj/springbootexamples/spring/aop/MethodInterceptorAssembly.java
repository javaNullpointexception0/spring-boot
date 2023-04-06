package com.lzj.springbootexamples.spring.aop;

import com.lzj.springbootexamples.service.impl.UserServiceImpl;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;

public class MethodInterceptorAssembly extends AbstractAutoProxyCreator {
    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> aClass, String s, TargetSource targetSource) throws BeansException {
        if (aClass == UserServiceImpl.class) {
            return new Object[]{new CustomizeMethodInterceptor()};
        }
        return null;
    }
}
