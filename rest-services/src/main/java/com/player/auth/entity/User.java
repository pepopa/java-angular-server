package com.player.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User implements IUser {

  @Id
  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private String password;
  private int age;

  public User() {
    // empty constructor - new database user
  }

  public User(IUser user) {
    this();
    this.id = user.getId();
  }

  public static User create(String firstName, int age) {
    User user = new User();
    user.setFirstName(firstName);
    user.setAge(age);
    return user;
  }

  @Override
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}