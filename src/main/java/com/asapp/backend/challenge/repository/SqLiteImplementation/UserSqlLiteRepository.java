package com.asapp.backend.challenge.repository.SqLiteImplementation;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.repository.UserRepository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Optional;

public class UserSqlLiteRepository implements UserRepository {
    private final Sql2o sql2o;

    public UserSqlLiteRepository(Sql2o datasource) {
        sql2o = datasource;
        createTable();
    }

    @Override
    public User addUser(User user) {
        try (Connection conn = sql2o.open()) {
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

    @Override
    public Optional<User> getByUsername(String username) {
        try (Connection conn = sql2o.open()) {
            User user = conn.createQuery("select * from users " +
                    "where username = :username")
                    .addParameter("username", username)
                    .addColumnMapping("id", "id")
                    .addColumnMapping("username", "username")
                    .addColumnMapping("creation_date", "creationDate")
                    .executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Connection conn = sql2o.open()) {
            User user = conn.createQuery("select * from users " +
                    "where id = :id")
                    .addParameter("id", id)
                    .addColumnMapping("id", "id")
                    .addColumnMapping("username", "username")
                    .addColumnMapping("creation_date", "creationDate")
                    .executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
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
