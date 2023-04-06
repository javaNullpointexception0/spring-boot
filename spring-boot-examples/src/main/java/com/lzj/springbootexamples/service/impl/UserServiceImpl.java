package com.lzj.springbootexamples.service.impl;

import com.lzj.springbootexamples.entity.User;
import com.lzj.springbootexamples.service.UserService;
import com.lzj.springbootexamples.utils.SpringUtil;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return (User)SpringUtil.getBean("user");
    }
}
