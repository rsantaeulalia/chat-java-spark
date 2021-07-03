package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.resources.MessageResource;

import java.util.Collection;

public interface MessageRepository {
    Message addMessage(MessageResource messageResource);

    Collection<Message> getMessages(Long receiverId, Long startId, Long limit);
}
