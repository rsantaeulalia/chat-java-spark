package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.exceptions.ContentTypeNotSupportedException;
import com.asapp.backend.challenge.model.Content;
import com.asapp.backend.challenge.model.ContentFactory;
import com.asapp.backend.challenge.model.Image;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.model.Text;
import com.asapp.backend.challenge.model.Video;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.ResultSetHandler;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.Date;

public class MessageSqlLiteRepository implements MessageRepository {
    private final Sql2o sql2o;

    public MessageSqlLiteRepository() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db", null, null);
        createTable();
    }

    @Override
    public Message addMessage(MessageRequest messageRequest) {
        try (Connection conn = sql2o.open()) {
            Message message = new Message(messageRequest.getSender(), messageRequest.getRecipient(),
                    new ContentFactory().create(messageRequest.getContent().getType(), messageRequest.getContent().getUrl(),
                            messageRequest.getContent().getHeight(), messageRequest.getContent().getWidth(), messageRequest.getContent().getText(),
                            messageRequest.getContent().getSource()),
                    new Date());
            Query query = conn.createQuery("insert into messages" +
                    "( sender_id, receiver_id, contentType, content, url, creation_date, source, width, height) " +
                    "VALUES (:sender_id,:receiver_id,:contentType,:content, :url, :creation_date, :source, :width, :height )")
                    .addParameter("sender_id", message.getSenderId())
                    .addParameter("receiver_id", message.getReceiverId())
                    .addParameter("creation_date", message.getCreationDate());

            query = setContent(query, message.getContent());

            Long id = query.executeUpdate().getKey(Long.class);

            message.setId(id);
            return message;
        }
    }

    @Override
    public Collection<Message> getMessages(Long receiverId, Long startId, Long limit) {
        try (Connection conn = sql2o.open()) {
            return conn
                    .createQuery("select * from messages " +
                            "where receiver_id = :receiver_id and id >= :start_id Limit :limit")
                    .addParameter("receiver_id", receiverId)
                    .addParameter("start_id", startId)
                    .addParameter("limit", limit)
                    .executeAndFetch(getMessagesHandler);
        } catch (Exception e) {
            throw e;
        }
    }

    private void createTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("CREATE TABLE IF NOT EXISTS messages (" +
                    "   id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "   sender_id INTEGER NOT NULL," +
                    "   receiver_id INTEGER NOT NULL," +
                    "   contentType TEXT NOT NULL," +
                    "   content TEXT NULL, " +
                    "   url TEXT NULL, " +
                    "   creation_date real NOT NULL," +
                    "   source TEXT INTEGER NULL," +
                    "   width INTEGER NULL," +
                    "   height INTEGER NULL," +
                    "   FOREIGN KEY (sender_id) REFERENCES users (id)," +
                    "   FOREIGN KEY (receiver_id) REFERENCES users (id) " +
                    ")"
            ).executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    private Query setContent(Query query, Content type) {
        if (type instanceof Image) {
            Image image = (Image) type;
            query.addParameter("url", image.getUrl())
                    .addParameter("width", image.getWidth())
                    .addParameter("height", image.getHeight())
                    .addParameter("contentType", image.getType());
        } else if (type instanceof Video) {
            Video video = (Video) type;
            query.addParameter("url", video.getUrl())
                    .addParameter("source", video.getSource())
                    .addParameter("contentType", video.getType());
        } else if (type instanceof Text) {
            Text text = (Text) type;
            query.addParameter("content", text.getText())
                    .addParameter("contentType", text.getType());
        } else {
            throw new ContentTypeNotSupportedException(type + "not supported");
        }

        return query;
    }

    ResultSetHandler<Message> getMessagesHandler = rs -> new Message(rs.getLong("sender_id"),
            rs.getLong("receiver_id"),
            new ContentFactory().create(rs.getString("contentType"),
                    rs.getString("url"),
                    rs.getInt("height"),
                    rs.getInt("width"),
                    rs.getString("content"),
                    rs.getString("source")),
            rs.getDate("creation_date"));

}
