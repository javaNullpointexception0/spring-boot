package com.lzj.service.impl;

import java.util.List;

import com.lzj.exception.OptimisticLockerException;
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
	public List<User> list() {
		return userMapper.list();
	}

	@Override
	public List<User> findUsersByName(String name) {
		return userMapper.findUsersByName(name);
	}

	/**
	 * 新增用户
	 * @param name
	 * @param age
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int addUser(String name, Integer age) {
		User user = new User();
		user.setName(name);
		user.setAge(age);
		return userMapper.insert(user);
	}
	/**
	 * 更新用户
	 * @param id
	 * @param name
	 * @param age
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateUser(Integer id, String name, Integer age) {
		//先查询，获取到版本号
		User dbUser = userMapper.selectById(id);
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setAge(age);
		//设置版本号
		user.setVersion(dbUser.getVersion());

		int updateCount = userMapper.updateById(user);
		if (updateCount <= 0) {
			throw new OptimisticLockerException("乐观锁检测到数据冲突，数据更新条数：" + updateCount);
		}
		return updateCount;
	}
}
