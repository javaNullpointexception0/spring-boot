package com.lzj.service;

import java.util.List;

import com.lzj.entities.User;

/**
 * @datetime: 2017年12月10日 上午9:23:27
 * @auth: luzhenjang
 * 描述：
 */
public interface UserService {

	public List<User> findUsersByName(String name);
	
	public User addUser(User user);
}
