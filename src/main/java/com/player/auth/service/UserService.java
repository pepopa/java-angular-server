package com.player.auth.service;

import com.player.auth.entity.IUser;
import com.player.auth.entity.UserMongo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserMongoRepository userMongoRepository;

  public IUser findByEmail(String email) {
    return findByEmailMongo(email);
  }

  private UserMongo findByEmailMongo(String email) {
    return userMongoRepository.findByEmail(email);
  }

  public List<String> getAllEmails() {
    return getAllEmailsMongo();
  }

  private List<String> getAllEmailsMongo() {
    return userMongoRepository.findAll().stream()
        .map(UserMongo::getEmail)
        .collect(Collectors.toList());
  }

  public IUser saveUser(IUser user) {
    return userMongoRepository.save(new UserMongo(user));
  }

  private IUser saveUserMongo(IUser user) {
    return userMongoRepository.save(new UserMongo(user));
  }
}
