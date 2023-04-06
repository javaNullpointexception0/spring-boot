package com.lzj.springbootexamples.controller;

import com.lzj.springbootexamples.entity.User;
import com.lzj.springbootexamples.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfpptc")
public class BeanFactoryPostProcessorTestController {

    @Value("${server.port}")
    private Integer serverPort;

    @Autowired
    private UserService userService;

    @RequestMapping("/createUser")
    public User createUser() {
        return new User();
    }

    @RequestMapping("/getUser")
    public User getUser() {
        return userService.getUser();
    }
}
