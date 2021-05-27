package com.lzj.springbootexamples.config;

import com.lzj.springbootexamples.spring.CustomImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CustomImportBeanDefinitionRegistrar.class)
public class CustomConfiguration {


    /*@Bean("user")
    public User initUser() {
        return new User();
    }*/
}
