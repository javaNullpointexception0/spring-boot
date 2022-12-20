package com.lzj.springbootexamples.config;

import com.lzj.springbootexamples.spring.aop.MethodInterceptorAssembly;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，负责装配类初始化
 */
@Configuration
public class MethodInterceptorConfiguration {

    @Bean
    public MethodInterceptorAssembly initMethodInterceptorAssembly() {
        return new MethodInterceptorAssembly();
    }
}
