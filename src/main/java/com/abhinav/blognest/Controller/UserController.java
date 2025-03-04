package com.abhinav.blognest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhinav.blognest.DTO.UserDto;
import com.abhinav.blognest.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	//create
		@PostMapping
		public ResponseEntity<UserDto> createUser(@RequestBody  UserDto userDto)
		{ 
			UserDto created = this.userService.createUser(userDto);
		    return new ResponseEntity<>(created,HttpStatus.CREATED);
			
		}
	
}
