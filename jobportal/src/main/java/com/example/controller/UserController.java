package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.Messages;
import com.example.dtos.ResponseDto;
import com.example.dtos.UserDto;
import com.example.dtos.UserResponse;
import com.example.dtos.UserResponseDto;
import com.example.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/saveuser")
	public ResponseEntity<ResponseDto> saveUser(@RequestBody UserDto user) {
		ResponseDto res = new ResponseDto();
		String message = userService.saveUser(user);
		if (message.equals(Messages.USER_EXISTS)) {
			res.setMessage(Messages.USER_EXISTS);
			res.setStatus(Messages.FAILED);
			res.setStatusCode(HttpStatus.CONFLICT.toString());
			return new ResponseEntity<ResponseDto>(res, HttpStatus.CONFLICT);
		}
		res.setMessage(Messages.CREATED);
		res.setStatus(Messages.SUCCESS);
		res.setStatusCode(HttpStatus.OK.toString());
		return new ResponseEntity<ResponseDto>(res, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<UserResponseDto> getAllUsers() {
		UserResponseDto dto = new UserResponseDto();
		List<UserResponse> list = userService.getAllUsers();
		if (list != null && !list.isEmpty()) {
			dto.setMessage(Messages.USERS_FETCHED);
			dto.setStatus(Messages.SUCCESS);
			dto.setStatusCode(HttpStatus.OK.toString());
			dto.setList(list);
			return new ResponseEntity<UserResponseDto>(dto, HttpStatus.OK);
		} else {
			dto.setMessage(Messages.FAILED);
			dto.setStatus(Messages.FAILED);
			dto.setStatusCode(HttpStatus.NO_CONTENT.toString());
			return new ResponseEntity<UserResponseDto>(dto, HttpStatus.NO_CONTENT);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<ResponseDto> userLogin(@RequestBody UserDto dto) {
		ResponseDto res = new ResponseDto();
		String message = userService.userLogin(dto);
		if (message.equals(Messages.SUCCESS)) {
			res.setMessage(Messages.LOGIN_SUCCESS);
			res.setStatus(Messages.SUCCESS);
			res.setStatusCode(HttpStatus.OK.toString());
			return new ResponseEntity<ResponseDto>(res, HttpStatus.OK);
		} else {
			res.setMessage(Messages.FAILED);
			res.setStatus(Messages.FAILED);
			res.setStatusCode(HttpStatus.UNAUTHORIZED.toString());
			return new ResponseEntity<ResponseDto>(res, HttpStatus.UNAUTHORIZED);
		}
	}

}
