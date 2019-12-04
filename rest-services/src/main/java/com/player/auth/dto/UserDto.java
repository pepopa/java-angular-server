package com.player.auth.dto;

import com.player.auth.entity.IUser;

public class UserDto implements IUser {

  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private String password;
  private int age;

  public UserDto(IUser user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.password = user.getPassword();
    this.age = user.getAge();
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
  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserDto{" +
        "email='" + email + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", age=" + age +
        '}';
  }
}
