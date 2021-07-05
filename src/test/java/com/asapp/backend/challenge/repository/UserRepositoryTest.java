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
import java.util.Optional;

public class UserRepositoryTest {

    static Sql2o sql2o = new Sql2o("jdbc:sqlite:sample-test.db", null, null);
    User userToSave;
    User savedUser;
    Optional<User> userFindByUsername;
    String username;
    UserRepository userRepository = new UserSqlLiteRepository(sql2o);

    @Before
    public void setUp() {
        deleteUsers();
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
        userToSave = new User("testingUsername", "passwordTest", new Date());
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
        givenUsernameToFind();
        whenTheRepositoryFindByUsername();
        thenTheUserIsFound();
    }

    private void givenUsernameToFind() {
        userRepository.addUser(new User("testingUsername2", "passwordTest2", new Date()));
        userRepository.addUser(new User("testingUsername3", "passwordTest3", new Date()));
        userRepository.addUser(new User("usernameToFind", "passwordTest4", new Date()));
        username = "usernameToFind";
    }

    private void whenTheRepositoryFindByUsername() {
        userFindByUsername = userRepository.getByUsername(username);
    }

    private void thenTheUserIsFound() {
        Assert.assertTrue(userFindByUsername.isPresent());
        Assert.assertEquals(userFindByUsername.get().getUsername(), username);
    }

    @Test
    public void testGetUserByWrongUsername() {
        givenWrongUsernameToFind();
        whenTheRepositoryFindByUsername();
        thenTheUserIsNotFound();
    }

    private void givenWrongUsernameToFind() {
        userRepository.addUser(new User("testingUsername2", "passwordTest2", new Date()));
        userRepository.addUser(new User("testingUsername3", "passwordTest3", new Date()));
        userRepository.addUser(new User("usernameToFind", "passwordTest4", new Date()));
        username = "wrongUsername";
    }

    private void thenTheUserIsNotFound() {
        Assert.assertTrue(userFindByUsername.isEmpty());
    }

    @AfterClass
    public static void clear() {
        dropUserTable();
    }

    private void deleteUsers() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DELETE FROM USERS").executeUpdate();
        }
    }

    private static void dropUserTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DROP TABLE IF EXISTS users").executeUpdate();
        }
    }
}
