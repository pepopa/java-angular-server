package com.player.auth.controller;

import com.mongodb.MongoClient;
import com.player.auth.dto.UserDto;
import com.player.auth.entity.IUser;
import com.player.auth.service.UserService;
import com.player.constants.ApplicationConstants;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(ApplicationConstants.API_VERSION + "/")
public class AuthorizationController {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  @Autowired
  private UserService userService;

  @Autowired
  private Environment env;

  @GetMapping("status")
  public ResponseEntity status() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("dbstatus")
  public ResponseEntity dbstatus() {
    try {
      MongoClient mongo = new MongoClient();
      mongo.getDB(env.getProperty("spring.data.mongodb.database"));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build();
    }
    return ResponseEntity.ok().build();
  }

  @GetMapping("users")
  public ResponseEntity<List<String>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllEmails(), HttpStatus.OK);
  }

  @GetMapping("users/login")
  public ResponseEntity<UserDto> login(@RequestParam String email, @RequestParam String password) {
    try {
      return authenticateUser(email, password);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  private ResponseEntity<UserDto> authenticateUser(String email, String password) {
    IUser user = null;
    try {
      user = userService.findByEmail(email);
      if (user == null) {
        throw new Exception(String.format("UserMongo not found for corresponding email: %s", email));
      }
      if (password == null || user.getPassword() == null
          || BCrypt.checkpw(password, user.getPassword())) {
        throw new Exception(String.format("Passwords does not match for email: %s", email));
      }
      return new ResponseEntity<>(new UserDto(user), HttpStatus.OK);
    } catch (Exception ex) {
      logger.log(Level.WARNING, ex.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping("users")
  public ResponseEntity<UserDto> register(@RequestBody UserDto user) {
    // TODO PPA - BCrypt.hashpw(password_from_user, BCrypt.gensalt());
    IUser storedUser = userService.saveUser(user);
    return new ResponseEntity<>(new UserDto(storedUser), HttpStatus.OK);
  }
} 