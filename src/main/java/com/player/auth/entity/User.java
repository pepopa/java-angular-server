package com.player.auth.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

  @Id
  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private String password;
  private int age;

  public User() {
  }

  public User(String firstName, int age) {
    this.firstName = firstName;
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User)o;

    if (age != user.age) {
      return false;
    }
    if (id != null ? !id.equals(user.id) : user.id != null) {
      return false;
    }
    if (email != null ? !email.equals(user.email) : user.email != null) {
      return false;
    }
    if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) {
      return false;
    }
    if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) {
      return false;
    }
    return password != null ? password.equals(user.password) : user.password == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + age;
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", email='" + email + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", password='" + password + '\'' +
        ", age=" + age +
        '}';
  }
}