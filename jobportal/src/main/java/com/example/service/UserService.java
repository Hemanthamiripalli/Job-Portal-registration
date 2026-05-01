package com.example.service;

import java.util.List;

import com.example.dtos.UserDto;
import com.example.dtos.UserResponse;

public interface UserService {

	public String saveUser(UserDto users);

	public List<UserResponse> getAllUsers();

	public String userLogin(UserDto dto);
}
