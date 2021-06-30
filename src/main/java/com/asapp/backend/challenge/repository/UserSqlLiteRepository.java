package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.time.LocalDate;

public class UserSqlLiteRepository implements UserRepository {
    private final Sql2o sql2o;

    public UserSqlLiteRepository() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db", null, null);
        createTable();
    }

    public User addUser(String username, String password) {
        try (Connection conn = sql2o.open()) {
            User user = new User(username, password, LocalDate.now());
            Long id = conn.createQuery("insert into users( username, password, creation_date) " +
                    "VALUES (:username, :password, :creation_date)")
                    .addParameter("username", user.getUsername())
                    .addParameter("password", user.getPassword())
                    .addParameter("creation_date", user.getCreationDate())
                    .executeUpdate()
                    .getKey(Long.class);
            user.setId(id);
            return user;
        }
    }

    private void createTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("CREATE TABLE IF NOT EXISTS users (" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "   username text NOT NULL," +
                    "   password text NOT NULL," +
                    "   creation_date real NOT NULL" +
                    ")"
            ).executeUpdate();
        }
    }
}
