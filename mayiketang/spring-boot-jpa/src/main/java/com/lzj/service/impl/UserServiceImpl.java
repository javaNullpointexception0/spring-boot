package com.lzj.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzj.entities.User;
import com.lzj.repository.UserRepository;
import com.lzj.service.UserService;

/**
 * @datetime: 2017年12月10日 上午9:23:44
 * @auth: luzhenjang
 * 描述：
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findUsersByName(String name) {
		return userRepository.findUsersByName(name);
	}

	@Transactional  //开启事务
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

}
