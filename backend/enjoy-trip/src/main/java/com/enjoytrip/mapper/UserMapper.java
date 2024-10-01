package com.enjoytrip.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.enjoytrip.dto.auth.User;

@Mapper
public interface UserMapper {

	@Update("UPDATE FROM user " +
			"SET token = NULL" +
			"WHERE id = #{id}")
	void expireToken(@Param("id") String id);
	@Select("SELECT * FROM user WHERE id = #{id} and password = #{password}")
	User findByIdAndPassword(@Param("id") String id, @Param("password") String password);
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	User findById(@Param("id") String id);
	
	@Insert("INSERT INTO user(id,email,password,name) VALUES (#{id}, #{email}, #{password}, #{name})")
	void create(
			@Param("id") String id,
			@Param("email") String email,
			@Param("password") String password,
			@Param("name") String name);
	
	@Select("SELECT * FROM user")
	List<User> findAll();
	
	@Delete("DELETE FROM user WHERE id = #{id}")
	int delete(@Param("id") String id);
	
	@Update("UPDATE user SET email = #{email}, password = #{password}, name = #{name} WHERE id = #{id}")
	int update(
			@Param("id") String id,
			@Param("email") String email, 
			@Param("password") String password, 
			@Param("name") String name);
	
}
