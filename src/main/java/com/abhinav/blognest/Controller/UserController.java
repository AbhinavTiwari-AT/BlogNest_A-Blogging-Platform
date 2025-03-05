package com.abhinav.blognest.Controller;

import java.util.List;

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
import com.abhinav.blognest.DTO.UserDto;
import com.abhinav.blognest.Entity.User;
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
	
		// put - update user
		@PutMapping("/{userId}")
		public ResponseEntity<User> updateUser(@RequestBody UserDto userDto,@PathVariable("userId")  Integer userId)
		{
			User updatedUser = this.userService.updateUser(userDto, userId);
			
			return ResponseEntity.ok(updatedUser);
		}
		
		//Delete - delete user
		@DeleteMapping("/{userId}")
		public String deleteUser(@PathVariable("userId")Integer userId )
		{
			this.userService.deleteUser()
			return "deleted";
		}
		
		//Get - user get
		
		@GetMapping("/")
		
		public ResponseEntity<List<UserDto>> getAllUser()
		{
			return ResponseEntity.ok(this.userService.getAllUser());
		}
		

		@GetMapping("/{userId}")
		
		public ResponseEntity<String> getSingleUser(@PathVariable Integer userId)
		{
			return ResponseEntity.ok(this.userService.getUserById(userId));
		}
		
}
