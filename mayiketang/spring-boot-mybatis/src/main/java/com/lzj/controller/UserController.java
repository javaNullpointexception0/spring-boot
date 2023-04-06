package com.lzj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.lzj.config.AppConfig;
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
	private com.lzj.config.AppConfig appConfig;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<User> list() {
		return userService.list();
	}

	@RequestMapping("/now")
	@ResponseBody
	public Long now() {
		return System.currentTimeMillis();
	}

	@RequestMapping("/start")
	public String start() throws Exception {
		Thread thread = new Thread(() -> {
			try {
				URL url = new URL("http://192.168.6.128:8081/user/list");
				System.out.println("开始时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				URLConnection urlConnection = url.openConnection();
				urlConnection.setConnectTimeout(appConfig.getConnectionTimeout());
				urlConnection.setReadTimeout(5000);
				InputStream inputStream = urlConnection.getInputStream();
				if (inputStream.available() > 0) {
					byte[] bytes = new byte[inputStream.available()];
					inputStream.read(bytes);
					System.out.println(new String(bytes));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("结束时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			System.out.println("请求完成");
		});
		thread.setDaemon(true);
		thread.start();
		Thread.sleep(500);
		System.out.println("返回结果");
		return "请求完成";
	}

	@RequestMapping("/findUsersByName/{name}")
	@ResponseBody
	public List<User> findUsersByName(@PathVariable("name") String name) {
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
