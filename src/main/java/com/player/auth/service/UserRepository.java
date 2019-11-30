package com.player.auth.service;

import com.player.auth.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {

  User findByFirstName(String firstName);

  User findByEmail(String email);

}