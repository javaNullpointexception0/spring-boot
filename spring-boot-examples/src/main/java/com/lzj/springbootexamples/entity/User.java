package com.lzj.springbootexamples.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    public User() {
        System.out.println("User 构造函数执行...");
    }


    private String name;
    private Integer age;
    private String gender = "male";

    public void init() {
        System.out.println("User 初始化函数执行...");
    }

    public void otherInit() {
        System.out.println("User 初始化函数otherInit()执行...");
    }
}
