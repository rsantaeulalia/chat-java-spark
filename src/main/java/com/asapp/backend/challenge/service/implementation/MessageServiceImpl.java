package com.asapp.backend.challenge.service.implementation;

import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.repository.MessageRepository;
import com.asapp.backend.challenge.service.MessageService;

import java.util.Collection;

public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
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
