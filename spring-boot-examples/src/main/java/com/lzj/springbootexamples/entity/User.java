package com.lzj.springbootexamples.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String name;
    private Integer age;
    private String gender = "male";
}
