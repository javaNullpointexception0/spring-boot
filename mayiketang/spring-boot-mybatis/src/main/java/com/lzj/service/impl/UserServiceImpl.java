package com.lzj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzj.entities.User;
import com.lzj.mapper.UserMapper;
import com.lzj.service.UserService;

/**
 * @datetime: 2017年12月10日 上午9:23:44
 * @auth: luzhenjang
 * 描述：
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public List<User> findUsersByName(String name) {
		return userMapper.findUsersByName(name);
	}

	@Transactional  //开启事务
	@Override
	public int addUser(String name, Integer age) {
		return userMapper.addUser(name, age);
	}

	

}
