package com.lzj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @datetime: 2017年12月10日 上午9:32:37
 * @auth: luzhenjang 
 * @classDesc：
 * 顶级版启动类，@SpringBootApplication会自动扫面所有子包的组件
 *        使用@MapperScan扫面mapper
 */
@SpringBootApplication
@MapperScan(basePackages = { "com.lzj.mapper" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
