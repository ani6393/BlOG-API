package com.blogging.Application.Services.impl;

import com.blogging.Application.Repositries.*;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.Application.Entities.User;
import com.blogging.Application.Payloads.UserDto;
import com.blogging.Application.Repositries.UserRepo;
import com.blogging.Application.Services.UserService;
import com.blogging.Application.Exceptions.*;

@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// Create the data Of user 
	
	@Override
	public UserDto createUser(UserDto userDto)
	{	
		User user=this.dtoToUser(userDto);
    User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	// Update the data of user
	
	
	@Override
	public UserDto updateUser(UserDto userdto, Integer userId)
	{
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

// Get the User Details By Id;	
	
	@Override
	public UserDto getUserById(Integer userId) 
	{

		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	
	//Get the Data of All Users in One time 
	
	@Override
	public List<UserDto> getAllUser() 
	{
		List<User> users=this.userRepo.findAll();
		
		List<UserDto>userDtos=users.stream().map(User->this.userToDto(User)).collect(Collectors.toList());
		return userDtos;
	}

	// Delete the data Of User By Id No.
	
	@Override
	public void deleteUser(Integer userId) {

		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		
		return user;

}
	public UserDto userToDto(User user) {
		
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
		
	}
}
