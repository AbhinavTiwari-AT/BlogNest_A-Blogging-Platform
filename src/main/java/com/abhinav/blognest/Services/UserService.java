package com.abhinav.blognest.Services;

import java.util.List;

import com.abhinav.blognest.DTO.PagebleResponse;
import com.abhinav.blognest.DTO.UserDto;

public interface UserService {
	
	//create
	UserDto createUser(UserDto userDto);
	
	//update
	UserDto updateUser(UserDto userDto,String userId);
	
	//get by id
	UserDto getById(String userId);
		
	//delete
	void deleteUser(String userId);
	
	//get all users
	PagebleResponse<UserDto> getAllUser(Integer pageNumber,Integer pageSize,String sortBy, String sortDir);
		
	//get single user by id
	UserDto getUserById(String userId);
		
	//get single user by email
	UserDto getUserByEmail(String email);
		
	//search user
	List<UserDto> searchUser(String keyword);
		
	

}
