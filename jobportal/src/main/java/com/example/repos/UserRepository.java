package com.example.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.models.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

}
