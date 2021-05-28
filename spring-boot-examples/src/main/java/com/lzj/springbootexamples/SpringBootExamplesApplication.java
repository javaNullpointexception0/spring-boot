package com.lzj.springbootexamples;

import com.lzj.springbootexamples.spring.CustomizeImportBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CustomizeImportBeanDefinitionRegistrar.class) //手动注册Bean
public class SpringBootExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExamplesApplication.class, args);
    }

}
