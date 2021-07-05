package com.asapp.backend.challenge.service;

import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.repository.MessageRepository;

import java.util.Collection;

public class MessageServiceTest implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceTest(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.addMessage(message);
    }

    @Override
    public Collection<Message> getMessagesByReceiverId(Long receiverId, Long startId, Long limit) {
        return messageRepository.getMessages(receiverId, startId, limit);
    }
}
