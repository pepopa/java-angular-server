package com.player.auth.service;

import com.player.auth.entity.IUser;
import com.player.auth.entity.User;
import com.player.auth.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public IUser findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<String> getAllEmails() {
    return StreamSupport.stream(userRepository.findAll().spliterator(), false)
        .map(User::getEmail)
        .collect(Collectors.toList());
  }

  public IUser saveUser(IUser user) {
    return userRepository.save(new User(user));
  }
}
