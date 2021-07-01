package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.model.User;

import java.util.Collection;
import java.util.Optional;

public interface MessageService {
    Message saveMessage(MessageRequest messageRequest);

    Collection<Message> getMessagesByReceiverId(Long receiverId, Long startId, Long limit);
}
