package com.lzj.springbootexamples.controller;

import com.lzj.springbootexamples.entity.User;
import com.lzj.springbootexamples.utils.SpringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfpptc")
public class BeanFactoryPostProcessorTestController {

    @RequestMapping("/createUser")
    public User createUser() {
        return new User();
    }

    @RequestMapping("/getUser")
    public User getUser() {
        return (User) SpringUtil.getBean("user");
    }
}
