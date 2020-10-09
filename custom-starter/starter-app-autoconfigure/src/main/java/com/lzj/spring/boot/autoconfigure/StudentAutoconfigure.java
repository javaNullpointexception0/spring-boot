package com.lzj.spring.boot.autoconfigure;

import com.lzj.entity.Student;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoconfigure {

    @Bean
    public Student initStudent(StudentProperties studentProperties) {
        Student student = new Student();
        student.setName(studentProperties.getName());
        student.setSex(studentProperties.getSex());
        student.setAge(studentProperties.getAge());
        return student;
    }
}
