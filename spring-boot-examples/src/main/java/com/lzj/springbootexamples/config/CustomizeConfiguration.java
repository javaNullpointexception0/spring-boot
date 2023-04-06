package com.lzj.springbootexamples.config;

import com.lzj.springbootexamples.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomizeConfiguration {

    @Bean(value = "user", initMethod = "init")
    public User initUser() {
        return new User();
    }
}
