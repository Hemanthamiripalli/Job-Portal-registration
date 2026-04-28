package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.Messages;
import com.example.dtos.ResponseDto;
import com.example.dtos.UserDto;
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

}
