package com.lzj.service;

import java.util.List;

import com.lzj.entities.User;

/**
 * @datetime: 2017年12月10日 上午9:23:27
 * @auth: luzhenjang
 * 描述：
 */
public interface UserService {

	List<User> list();
	
	List<User> findUsersByName(String name);
	
	int addUser(String name, Integer age);

	int updateUser(Integer id, String name, Integer age);
}
