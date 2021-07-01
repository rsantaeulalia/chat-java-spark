package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.resources.MessageResource;

import java.util.Optional;

public interface MessageRepository {
    User addMessage(MessageResource messageResource);

    Optional<User> getMessages(Long receiverId, Long startId, Long limit);
}
