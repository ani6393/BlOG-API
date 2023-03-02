package com.blogging.Application.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto 
{

	private int id;
	
	@NotEmpty
	@Size(min=3,message = "User name min of 3 Character !!")
	private String name;
	
	@Email(message = "Your Email Address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min = 4,max = 10,message = "Password must be min of 3 chars and max of 10 !!")
	private String password;
	
	@NotBlank
	private String about;
}
