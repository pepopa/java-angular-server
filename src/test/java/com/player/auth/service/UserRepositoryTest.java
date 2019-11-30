package com.player.auth.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.player.auth.entity.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userMongoRepository;

  @Before
  public void setUp() throws Exception {
    User user1 = new User("Alice", 23);
    User user2 = new User("Bob", 38);
    //save product, verify has ID value after save
    assertNull(user1.getId());
    assertNull(user2.getId());//null before save
    this.userMongoRepository.save(user1);
    this.userMongoRepository.save(user2);
    assertNotNull(user1.getId());
    assertNotNull(user2.getId());
  }

  @Test
  public void testFetchData() {
        /*Test data retrieval*/
    User userA = userMongoRepository.findByFirstName("Bob");
    assertNotNull(userA);
    assertEquals(38, userA.getAge());
        /*Get all products, list should only have two*/
    Iterable<User> users = userMongoRepository.findAll();
    int count = 0;
    for (User p : users) {
      count++;
    }
    assertEquals(2, count);
  }

  @Test
  public void testDataUpdate() {
        /*Test update*/
    User userB = userMongoRepository.findByFirstName("Bob");
    userB.setAge(40);
    userMongoRepository.save(userB);
    User userC = userMongoRepository.findByFirstName("Bob");
    assertNotNull(userC);
    assertEquals(40, userC.getAge());
  }

  @After
  public void tearDown() throws Exception {
    this.userMongoRepository.deleteAll();
  }
}