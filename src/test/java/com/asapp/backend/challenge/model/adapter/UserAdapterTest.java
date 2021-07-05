package com.asapp.backend.challenge.model.adapter;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.UserResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserAdapterTest {

    private UserRequest userRequest;
    private UserResource userResource;
    private UserResource expectedUserResource;
    private User user;
    private User expectedUser;

    @Before
    public void setUp() {
        userRequest = null;
        userResource = null;
        expectedUserResource = null;
        user = null;
        expectedUser = null;
    }

    @Test
    public void adaptFromApiToDomainModel() {
        givenAUserRequest();
        whenUserAdapterToDomainIsCalled();
        thenUserIsReturned();
    }

    private void givenAUserRequest() {
        userRequest = new UserRequest("testingUser", "pa$$word123");
        expectedUser = new User("testingUser", "pa$$word123", new Date());
    }

    private void whenUserAdapterToDomainIsCalled() {
        user = UserAdapter.toDomain(userRequest);
    }

    private void thenUserIsReturned() {
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(), expectedUser.getUsername());
        Assert.assertEquals(user.getPassword(), expectedUser.getPassword());
    }

    @Test
    public void adaptFromDomainToApiModel() {
        givenAUser();
        whenUserAdapterToApiIsCalled();
        thenUserResourceIsReturned();
    }

    private void givenAUser() {
        user = new User(1L, "testUsername", "passwordtest", new Date());
        expectedUserResource = new UserResource(1L);
    }

    private void whenUserAdapterToApiIsCalled() {
        userResource = UserAdapter.toApi(user);
    }

    private void thenUserResourceIsReturned() {
        Assert.assertNotNull(userResource);
        Assert.assertEquals(userResource.getId(), expectedUserResource.getId());
    }
}
