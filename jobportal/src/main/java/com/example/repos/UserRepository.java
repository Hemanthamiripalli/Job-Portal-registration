package com.example.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.models.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	boolean existsByEmail(String email);
	
	Users findByEmail(String email);
	
}
