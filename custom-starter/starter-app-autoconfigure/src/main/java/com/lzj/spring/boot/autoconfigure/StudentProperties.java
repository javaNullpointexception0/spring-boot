package com.lzj.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student")
public class StudentProperties {
    private String name;
    private String sex;
    private Integer age;
}
