package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dtos.Messages;
import com.example.dtos.UserDto;
import com.example.dtos.UserResponse;
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

	@Override
	public List<UserResponse> getAllUsers() {
		List<UserResponse> res = new ArrayList<UserResponse>();

		List<Users> list = repo.findAll();
		for (Users u : list) {
			UserResponse user = new UserResponse();
			user.setId(u.getId());
			user.setEmail(u.getEmail());
			user.setRole(u.getRole());
			res.add(user);
		}
		return res;
	}

	@Override
	public String userLogin(UserDto dto) {

		if (dto != null) {
			Users user = repo.findByEmail(dto.getEmail());
			boolean match = passwordEncoder.matches(dto.getPassword(), user.getPassword());
			if (match) {
				return Messages.SUCCESS;
			} else {
				return Messages.FAILED;
			}

		} else {
			return Messages.NOT_FOUND;
		}
	}
}
