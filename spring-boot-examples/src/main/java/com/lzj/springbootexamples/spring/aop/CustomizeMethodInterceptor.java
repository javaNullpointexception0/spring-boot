package com.lzj.springbootexamples.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 自定义方法拦截器，支持自定义调用目标方法前后的的逻辑
 */
public class CustomizeMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("调用目标方法前的自定义逻辑");
        Object result = methodInvocation.proceed();
        System.out.println("调用目标方法后的自定义逻辑");
        return result;
    }
}
