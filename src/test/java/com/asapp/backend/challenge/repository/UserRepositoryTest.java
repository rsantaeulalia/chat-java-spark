package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.SqLiteImplementation.UserSqlLiteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.util.Date;

public class UserRepositoryTest {

    User userToSave;
    User savedUser;
    UserRepository userRepository = new UserSqlLiteRepository(new Sql2o("jdbc:sqlite:sample-test.db", null, null));

    @Test
    public void testSaveUser() {
        givenUserToSave();
        whenTheRepositoryIsCalledToSave();
        thenTheUserIsCorrectlySaved();
    }

    private void givenUserToSave() {
        userToSave = new User("TestingUser", "passwordTest", new Date());
    }

    private void whenTheRepositoryIsCalledToSave() {
        savedUser = userRepository.addUser(userToSave);
    }

    private void thenTheUserIsCorrectlySaved() {
        Assert.assertNotNull(savedUser);
        Assert.assertEquals(1, savedUser.getId().longValue());
        Assert.assertEquals(userToSave.getUsername(), savedUser.getUsername());
        Assert.assertEquals(userToSave.getPassword(), savedUser.getPassword());
        Assert.assertEquals(userToSave.getCreationDate(), savedUser.getCreationDate());
    }

    @Test
    public void testGetUserByUsername() {

    }
}
