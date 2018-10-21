package com.lzj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.lzj.entities.User;

/**
 * @datetime: 2017年12月10日 上午9:24:54
 * @auth: luzhenjang
 * 描述：
 */
public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

	public List<User> findUsersByName(String name);
}
