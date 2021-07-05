package com.asapp.backend.challenge.service.implementation;

import com.asapp.backend.challenge.exceptions.UserNotFoundException;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.repository.MessageRepository;
import com.asapp.backend.challenge.service.MessageService;
import com.asapp.backend.challenge.service.UserService;

import java.util.Collection;

public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public MessageServiceImpl(MessageRepository messageRepository,
                              UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.addMessage(message);
    }

    @Override
    public Collection<Message> getMessagesByReceiverId(Long receiverId, Long startId, Long limit) {
        if (userService.getUserById(receiverId).isPresent()) {
            return messageRepository.getMessages(receiverId, startId, limit);
        }

        throw new UserNotFoundException(String.format("User id %s not found", receiverId));
    }
}
