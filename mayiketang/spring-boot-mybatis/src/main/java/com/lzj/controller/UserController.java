package com.lzj.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lzj.entities.User;
import com.lzj.service.UserService;

/**
 * @datetime: 2017年12月10日 上午9:20:20
 * @auth: luzhenjang 
 * 描述：演示mybatis的使用 
 * 1、pom.xml引入相关的jar依赖
 * 2、application.properties配置数据源连接信息 3、开发controller/service/dao代码
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<User> list() {
		return userService.list();
	}

	@RequestMapping("/findUsersByName/{name}")
	@ResponseBody
	public List<User> findUsersByName(@PathVariable("name") String name) {
		try {
			System.out.println(System.currentTimeMillis());
			Thread.sleep(60 * 60 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return userService.findUsersByName(name);
	}

	@RequestMapping("/addUser/{name}/{age}")
	public String addUser(@PathVariable("name") String name, @PathVariable("age") Integer age) {
		int flg = userService.addUser(name, age);
		return flg + "";
	}
}
