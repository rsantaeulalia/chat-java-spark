package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.AbstractFactory;
import com.asapp.backend.challenge.model.Content;
import com.asapp.backend.challenge.model.ContentFactory;
import com.asapp.backend.challenge.model.Image;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.model.Text;
import com.asapp.backend.challenge.model.Video;
import com.asapp.backend.challenge.model.enums.SourceEnum;
import com.asapp.backend.challenge.resources.MessageResource;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class MessageSqlLiteRepository implements MessageRepository {
    private final Sql2o sql2o;

    public MessageSqlLiteRepository() {
        sql2o = new Sql2o("jdbc:sqlite:sample.db", null, null);
        createTable();
    }

    @Override
    public Message addMessage(MessageResource messageResource) {
        try (Connection conn = sql2o.open()) {
            Message message = new Message(messageResource.getSender(), messageResource.getRecipient(),
                    new ContentFactory().create(messageResource.getContent().getType(), messageResource.getContent().getUrl(),
                            messageResource.getContent().getHeight(), messageResource.getContent().getWidth(), messageResource.getContent().getText(),
                            SourceEnum.valueOf(messageResource.getContent().getSource())),
                    new Date());
            Query query = conn.createQuery("insert into messages" +
                    "( sender_id,receiver_id,contentType, content, url, date_created, source, width, height) " +
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
            throw new IllegalArgumentException(type + "not supported");
        }

        return query;
    }

    @Override
    public Collection<Message> getMessages(Long receiverId, Long startId, Long limit) {
        return Set.of();
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
            System.out.println(e.getCause());
        }
    }
}
