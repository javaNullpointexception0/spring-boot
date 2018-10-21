package com.lzj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lzj.entities.User;


/**
 * @datetime: 2017年12月10日 上午9:24:54
 * @auth: luzhenjang
 * 描述：
 */
@Mapper
public interface UserMapper {
	
	@Select("select * from tbUser where name=#{name}")
	public List<User> findUsersByName(@Param("name") String name);
	
	@Insert("insert into tbUser(name, age) values(#{name},#{age})")
	public int addUser(@Param("name") String name, @Param("age") Integer age);
}
