package com.player.auth.repository;

import com.player.auth.entity.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryIT {

  @Autowired
  private UserRepository userMongoRepository;

  @Before
  public void setUp() throws Exception {
    User user1 = User.create("Alice", 23);
    User user2 = User.create("Bob", 38);
    //save product, verify has ID value after save
    Assert.assertNull(user1.getId());
    Assert.assertNull(user2.getId());//null before save
    this.userMongoRepository.save(user1);
    this.userMongoRepository.save(user2);
    Assert.assertNotNull(user1.getId());
    Assert.assertNotNull(user2.getId());
  }

  @Test
  public void testFetchData() {
    User userA = userMongoRepository.findByFirstName("Bob");
    Assert.assertNotNull(userA);
    Assert.assertEquals(38, userA.getAge());
    Iterable<User> users = userMongoRepository.findAll();
    int count = 0;
    for (User p : users) {
      count++;
    }
    Assert.assertEquals(2, count);
  }

  @Test
  public void testDataUpdate() {
    User userB = userMongoRepository.findByFirstName("Bob");
    userB.setAge(40);
    userMongoRepository.save(userB);
    User userC = userMongoRepository.findByFirstName("Bob");
    Assert.assertNotNull(userC);
    Assert.assertEquals(40, userC.getAge());
  }

  @After
  public void tearDown() throws Exception {
    this.userMongoRepository.deleteAll();
  }
}