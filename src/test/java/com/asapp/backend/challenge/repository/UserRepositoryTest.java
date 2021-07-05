package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.SqLiteImplementation.UserSqlLiteRepository;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Date;

public class UserRepositoryTest {

    static Sql2o sql2o = new Sql2o("jdbc:sqlite:sample-test.db", null, null);
    User userToSave;
    User savedUser;
    UserRepository userRepository = new UserSqlLiteRepository(sql2o);

    @Before
    public void setUp() {
        userToSave = null;
        savedUser = null;
    }

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

    @AfterClass
    public static void clear() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DROP TABLE IF EXISTS users").executeUpdate();
        }
    }
}
