package com.lzj;

import com.lzj.customjmxbean.TestJMXBeanRegistryListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActuatorApplication.class, args);
    }

//    @Bean
//    public ApplicationListener applicationEventListener() {
//        TestJMXBeanRegistryListener testJMXBeanRegistryListener = new TestJMXBeanRegistryListener();
//        return testJMXBeanRegistryListener;
//    }
}
