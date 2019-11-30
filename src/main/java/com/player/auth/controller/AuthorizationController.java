package com.player.auth.controller;

import com.player.auth.entity.User;
import com.player.auth.service.UserRepository;
import com.player.constants.ApplicationConstants;

import java.util.List;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(ApplicationConstants.API_VERSION + "/")
public class AuthorizationController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping("status")
  public BodyBuilder status() {
    return ResponseEntity.ok();
  }

  @GetMapping("users")
  public ResponseEntity<List<String>> getAllUsers() {
    List<String> emails = userRepository.findAll().stream()
        .map(User::getEmail)
        .collect(Collectors.toList());
    return new ResponseEntity<>(emails, HttpStatus.OK);
  }

  @GetMapping("users/login")
  public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {
    try {
      return authenticateUser(email, password);
    } catch (Exception ex) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  private ResponseEntity<User> authenticateUser(@RequestParam String email, @RequestParam String password) {
    User user = userRepository.findByEmail(email);
    if (password != null
        && user != null
        && user.getPassword() != null
        && BCrypt.checkpw(password, user.getPassword())) {
      return new ResponseEntity<>(new User(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("users")
  public ResponseEntity<User> register(@RequestBody User user) {
    // TODO PPA - BCrypt.hashpw(password_from_user, BCrypt.gensalt());
    userRepository.save(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
} 