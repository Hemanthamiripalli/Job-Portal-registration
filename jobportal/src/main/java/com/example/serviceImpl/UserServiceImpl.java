package com.example.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dtos.Messages;
import com.example.dtos.UserDto;
import com.example.models.Users;
import com.example.repos.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository repo;

	@Override
	public String saveUser(UserDto user) {
		Users users = new Users();
		boolean found = repo.existsByEmail(user.getEmail());
		if (!found) {
			users.setEmail(user.getEmail());
		} else {
			return Messages.USER_EXISTS;
		}
		users.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(users);
		return null;
	}
}
