package com.abhinav.blognest.Services.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abhinav.blognest.DTO.PagebleResponse;
import com.abhinav.blognest.DTO.UserDto;
import com.abhinav.blognest.Entity.User;
import com.abhinav.blognest.Exception.ResourceNotFoundException;
import com.abhinav.blognest.Helper.Helper;
import com.abhinav.blognest.Repositories.UserRepo;
import com.abhinav.blognest.Services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		String userId = UUID.randomUUID().toString();
		                userDto.setUserId(userId);
		
		User user = this.modelMapper.map(userDto,User.class);
		User created = this.userRepo.save(user);
	
		return modelMapper.map(created,UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto userDto, String userId) {

		User user = this.userRepo.findById(userId).orElseThrow();
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updated = this.userRepo.save(user);
		
		return modelMapper.map(updated,UserDto.class);
	}

	
	@Override
	public UserDto getById(String userId) {

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
		return modelMapper.map(user, UserDto.class);
	}
 

	@Override
	public void deleteUser(String userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
		           this.userRepo.delete(user);
	}

	@Override
	public PagebleResponse<UserDto> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		//int PageSize=5;
		//int pageNumber=1;
		
		Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
				
        Page<User> userPage = this.userRepo.findAll(p);
        PagebleResponse<UserDto> response  = Helper.getPagebleResponse(userPage,UserDto.class);
	   
		return response;
	}

	

	@Override
	public UserDto getUserById(String userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId",userId));
	
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {

		User user= userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User","Useremail",email));
		
		return this.modelMapper.map(user,UserDto.class);
		
	}

	@Override
	public List<UserDto> searchUser(String keyword) {

		List<User> users = userRepo.findByNameContaining(keyword);
		List<UserDto> dtoList = users.stream().map(user -> this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
		return dtoList;
	}

}
