package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.Message;

import java.util.Collection;

public interface MessageRepository {
    Message addMessage(Message message);

    Collection<Message> getMessages(Long receiverId, Long startId, Long limit);
}
