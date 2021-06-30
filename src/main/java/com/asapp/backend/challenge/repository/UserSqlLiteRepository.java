package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;
import org.sql2o.Sql2o;

public class UserSqlLiteRepository implements UserRepository {
    private Sql2o sql2o;

    public UserSqlLiteRepository() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db",null,null);
    }

    public User addUser(User user) {
        return null;
    }
}
