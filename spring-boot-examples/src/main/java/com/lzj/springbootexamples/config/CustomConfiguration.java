package com.lzj.springbootexamples.config;

import com.lzj.springbootexamples.entity.User;
import com.lzj.springbootexamples.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfiguration {

    @Bean(value = "user", initMethod = "init")
    public User initUser() {
        return new User();
    }
}
