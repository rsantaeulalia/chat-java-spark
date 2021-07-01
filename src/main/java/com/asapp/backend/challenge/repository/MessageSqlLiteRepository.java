package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.MessageResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Optional;

public class MessageSqlLiteRepository implements MessageRepository {
    private final Sql2o sql2o;

    public MessageSqlLiteRepository() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db", null, null);
        createTable();
    }

    @Override
    public User addMessage(MessageResource messageResource) {
        return null;
    }

    @Override
    public Optional<User> getMessages(Long receiverId, Long startId, Long limit) {
        return Optional.empty();
    }

    private void createTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("CREATE TABLE IF NOT EXISTS messages (" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "   sender_id INTEGER NOT NULL," +
                    "   receiver_id INTEGER NOT NULL," +
                    "   contentType text NOT NULL," +
                    "   content text NOT NULL, " +
                    "   answered NUMERIC NOT NULL, " +
                    "   date_created real NOT NULL," +
                    "   metadata_id INTEGER " +
                    ")"
            ).executeUpdate();
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
}
