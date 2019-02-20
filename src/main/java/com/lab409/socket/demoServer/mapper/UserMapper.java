package com.lab409.socket.demoServer.mapper;

import com.lab409.socket.demoServer.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
	
	@Select("SELECT * FROM user")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "name", column = "name"),
			@Result(property = "pwd", column="pwd")
	})
	List<User> getAll();
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "name", column = "name"),
			@Result(property = "pwd", column="pwd")
	})
	User getOne(Long id);

	@Insert("INSERT INTO user(name,pwd) VALUES(#{name}, #{pwd})")
	@Options(useGeneratedKeys = true,keyProperty = "id")
	void insert(User user);

	@Update("UPDATE user SET name=#{name}, pwd=#{pwd} WHERE id =#{id}")
	void update(User user);

	@Delete("DELETE FROM user WHERE id =#{id}")
	void delete(Long id);

}