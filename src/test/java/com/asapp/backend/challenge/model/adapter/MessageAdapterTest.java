package com.asapp.backend.challenge.model.adapter;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.model.ContentFactory;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.resources.MessageResponseResource;

import java.util.Date;

public class MessageAdapterTest {
    public static Message toDomain(MessageRequest messageRequest) {
        return new Message(messageRequest.getSender(), messageRequest.getRecipient(),
                new ContentFactory().create(messageRequest.getContent().getType(), messageRequest.getContent().getUrl(),
                        messageRequest.getContent().getHeight(), messageRequest.getContent().getWidth(), messageRequest.getContent().getText(),
                        messageRequest.getContent().getSource()),
                new Date());
    }

    public static MessageResource toApi(Message message) {
        return new MessageResource(message.getId(), message.getCreationDate(), message.getSenderId(), message.getReceiverId(),
                ContentAdapterTest.toApi(message.getContent()));
    }

    public static MessageResponseResource toMessageResponse(Message message) {
        return new MessageResponseResource(message.getId(), message.getCreationDate());
    }
}
