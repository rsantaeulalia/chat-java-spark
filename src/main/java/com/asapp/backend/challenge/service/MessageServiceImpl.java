package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.repository.MessageRepository;

import java.util.Collection;

public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final TokenValidatorService tokenValidatorService;

    public MessageServiceImpl(MessageRepository messageRepository,
                              TokenValidatorService tokenValidatorService) {
        this.messageRepository = messageRepository;
        this.tokenValidatorService = tokenValidatorService;
    }

    @Override
    public Message saveMessage(MessageRequest messageRequest) {
        return messageRepository.addMessage(messageRequest);
    }

    @Override
    public Collection<Message> getMessagesByReceiverId(Long receiverId, Long startId, Long limit) {
        return null;
    }
}
