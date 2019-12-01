package com.player.auth.service;

import com.player.auth.entity.UserMongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<UserMongo, Integer> {

  UserMongo findByFirstName(String firstName);

  UserMongo findByEmail(String email);

}