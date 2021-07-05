package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.Message;

import java.util.Collection;

public interface MessageService {
    Message saveMessage(Message message);

    Collection<Message> getMessagesByReceiverId(Long receiverId, Long startId, Long limit);
}
