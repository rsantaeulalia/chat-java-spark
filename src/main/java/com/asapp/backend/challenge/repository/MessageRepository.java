package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.model.Message;

import java.util.Collection;

public interface MessageRepository {
    Message addMessage(MessageRequest messageRequest);

    Collection<Message> getMessages(Long receiverId, Long startId, Long limit);
}
