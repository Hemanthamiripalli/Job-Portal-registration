package com.example.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Users;


@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	boolean existsByEmail(String email);
	
}
