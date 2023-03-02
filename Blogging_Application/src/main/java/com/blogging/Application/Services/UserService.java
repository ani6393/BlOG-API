package com.blogging.Application.Services;

import java.util.List;

import com.blogging.Application.Entities.User;
import com.blogging.Application.Payloads.UserDto;

public interface UserService 
{

	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto>getAllUser();
	
	void deleteUser(Integer userId);
	
}
