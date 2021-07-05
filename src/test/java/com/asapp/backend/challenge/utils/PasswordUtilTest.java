package com.asapp.backend.challenge.utils;

import org.junit.Assert;
import org.junit.Test;

public class PasswordUtilTest {

    private String password = "Pa$$word";
    private String hashedPassword = "$2a$10$7ucFCkXSDNU5Sx93ySjSFe1PK8Ejr4ZiW8sRVfvEHWGnnul.HO8Jy";

    @Test
    public void givenHashedPasswordCheckIsCorrect() {
        Assert.assertTrue(PasswordUtil.checkPassword(password, hashedPassword));
    }
}
