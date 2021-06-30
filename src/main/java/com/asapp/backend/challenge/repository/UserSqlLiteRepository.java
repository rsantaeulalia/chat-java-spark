package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserSqlLiteRepository implements UserRepository {
    private JdbcTemplate template;

    @Autowired
    public UserSqlLiteRepository(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    public User addUser(User user) {
        return null;
    }
}
