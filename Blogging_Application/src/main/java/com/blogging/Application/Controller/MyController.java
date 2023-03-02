package com.blogging.Application.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.Application.Payloads.ApiResponse;
import com.blogging.Application.Payloads.UserDto;
import com.blogging.Application.Services.UserService;

import jakarta.validation.Valid;


@RestController

@RequestMapping("/api/users")
public class MyController 
{
	@Autowired
	private UserService userService;
	
	// POST-Create User
	
	@PostMapping("/")
	public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	// PUT- update user
	
	@PutMapping("/{userId}")
		public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Integer uId){
		UserDto updatedUser = this.userService.updateUser(userDto, uId);
	return ResponseEntity.ok(updatedUser);
	}
	
	// DELETE- 1st Type [User Details] BY MAP;
	
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<?>deleteUser(@PathVariable("userId")Integer uId) 
//	{
//		this.userService.deleteUser(uId);
//		return new ResponseEntity(Map.of("message","User Deleted SuccessFully"),HttpStatus.OK);
//		
//	}
	
	//DELETE - 2nd Second Type [User Details] By ApiResponse;
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId")Integer uId){
		this.userService.deleteUser(uId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	
	// GET- User getAll
	
@GetMapping("/")
public ResponseEntity<List<UserDto>> getAllUsers(){
	return ResponseEntity.ok(this.userService.getAllUser());
}


//GET- User getOne
@GetMapping("/{userId}")
public ResponseEntity<UserDto> getUser(@PathVariable("userId")Integer uId){
	return ResponseEntity.ok(this.userService.getUserById(uId));
}
}