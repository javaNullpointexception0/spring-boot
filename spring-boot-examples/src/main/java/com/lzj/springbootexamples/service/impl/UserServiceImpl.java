package com.lzj.springbootexamples.service.impl;

import com.lzj.springbootexamples.entity.User;
import com.lzj.springbootexamples.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Created by luzhenjiang
 * @date 2023/4/6 16:05
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(Long userId) {
        return new User();
    }
}
