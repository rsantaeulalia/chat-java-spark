package com.asapp.backend.challenge.service;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import com.asapp.backend.challenge.exceptions.UsernameAlreadyExistsException;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.UserRepository;
import com.asapp.backend.challenge.service.implementation.UserServiceImpl;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Optional;

public class UserServiceTest {
    private final User user = new User("Testusername", "hashedPassword", new Date());
    private final User expectedUser = new User(1L, "Testusername", "hashedPassword", new Date());

    private UserService userService;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = EasyMock.createMock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void givenAUserWhenServiceSaveMethodIsCalledThenReturnUser() {
        expect(userRepository.getByUsername(user.getUsername())).andReturn(Optional.empty());
        expect(userRepository.addUser(user)).andReturn(expectedUser);

        replay(userRepository);

        User savedUser = userService.registerUser(user);

        Assert.assertEquals(savedUser.getId(), expectedUser.getId());

        verify(userRepository);
    }

    @Test(expected = UsernameAlreadyExistsException.class)
    public void givenAUserWithExistingUsernameWhenServiceSaveMethodIsCalledThenThrowException() {
        expect(userRepository.getByUsername(user.getUsername())).andReturn(Optional.of(user));

        replay(userRepository);

        userService.registerUser(user);

        verify(userRepository);
    }

    @Test
    public void givenUsernameWhenGetByUsernameIsCalledThenReturnUser() {
        expect(userRepository.getByUsername("Testusername")).andReturn(Optional.of(expectedUser));

        replay(userRepository);

        Optional<User> savedUser = userService.getUserByUsername("Testusername");

        Assert.assertTrue(savedUser.isPresent());
        Assert.assertEquals(savedUser.get().getUsername(), expectedUser.getUsername());
        Assert.assertEquals(savedUser.get().getPassword(), expectedUser.getPassword());


        verify(userRepository);
    }

    @Test
    public void givenWrongUsernameWhenGetByUsernameIsCalledThenReturnEmpty() {
        expect(userRepository.getByUsername("wrongtestusername")).andReturn(Optional.empty());

        replay(userRepository);

        Optional<User> savedUser = userService.getUserByUsername("wrongtestusername");

        Assert.assertTrue(savedUser.isEmpty());

        verify(userRepository);
    }
}
