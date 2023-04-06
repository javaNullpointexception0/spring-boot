package com.lzj.springbootexamples.service;

import com.lzj.springbootexamples.entity.User;

/**
 * @author Created by luzhenjiang
 * @date 2023/4/6 16:03
 * @description
 */
public interface UserService {

    /**
     * 根据用户id获取用户信息
     * @return User
     */
    User getUser();
}
