package com.lzj.springbootexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import(CustomImportBeanDefinitionRegistrar.class) 手动注册Bean
public class SpringBootExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExamplesApplication.class, args);
    }

}
