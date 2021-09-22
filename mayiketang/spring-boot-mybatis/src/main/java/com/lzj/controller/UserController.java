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

	@RequestMapping("/updateUser/{id}/{name}/{age}")
	public String updateUser(@PathVariable("id") Integer id, @PathVariable("name") String name,
							 @PathVariable("age") Integer age) {
		return userService.updateUser(id, name, age) + "";
	}

	@RequestMapping("/updateUserInThread/{id}/{name}/{age}")
	public String updateUserInThread(@PathVariable("id") Integer id, @PathVariable("name") String name,
							 @PathVariable("age") Integer age) throws InterruptedException {
		AtomicInteger failTime = new AtomicInteger(0);
		AtomicInteger successTime = new AtomicInteger(0);
		Thread thread1 = startTask(id, name, age, failTime, successTime);
		Thread thread2 = startTask(id, name, age, failTime, successTime);
		thread1.join();
		thread2.join();
		System.out.println("成功次数：" + successTime.get() + "，失败次数：" + failTime.get());
		return "并发测试完成";
	}

	private Thread startTask(Integer id, String name, Integer age, AtomicInteger failTime, AtomicInteger successTime) {
		long endTime = System.currentTimeMillis() + 5000L;
		Thread thread = new Thread(() -> {
			while (true) {
				if (System.currentTimeMillis() - endTime > 0) {
					break;
				}
				try {
					int flg = userService.updateUser(id, name, age);
					successTime.incrementAndGet();
				} catch (Exception e) {
					failTime.incrementAndGet();
				}
			}
		});
		thread.start();
		return thread;
	}
}
