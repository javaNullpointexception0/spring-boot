package com.lzj.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.lzj.entities.User;


/**
 * @datetime: 2017年12月10日 上午9:24:54
 * @auth: luzhenjang
 * 描述：
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
	
	@Select("select * from tbUser")
	List<User> list();
	
	@Select("select * from tbUser where name=#{name}")
	List<User> findUsersByName(@Param("name") String name);

	@Update("update tbUser set name=#{name}, age=#{age}, version=version+1 where id=#{id} and version=#{version}")
	int updateByIdInSql(User user);
}
