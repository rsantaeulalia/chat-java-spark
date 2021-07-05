package com.asapp.backend.challenge.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static final String BCRYPT_SALT = BCrypt.gensalt(10);

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCRYPT_SALT);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
