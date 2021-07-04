package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.resources.MessageResponseResource;
import com.asapp.backend.challenge.service.MessageService;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.google.common.collect.ImmutableList;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MessagesController {

    private static final Long DEFAULT_LIMIT = 10L;
    public static final String START_PROPERTY = "start";
    public static final String LIMIT_PROPERTY = "limit";
    public static final String RECIPIENT_PROPERTY = "recipient";

    private MessageService messageService;

    public MessagesController(final MessageService messageService) {
        this.messageService = messageService;
    }

    public Route sendMessage = (Request req, Response rep) -> {
        MessageRequest messageRequest = JSONUtil.jsonToData(req.body(), MessageRequest.class);
        Message message = messageService.saveMessage(messageRequest);
        return JSONUtil.dataToJson(new MessageResponseResource(message.getId(), message.getCreationDate()));
    };

    public Route getMessages = (Request req, Response rep) -> {
        Long limit = req.queryParams().contains(LIMIT_PROPERTY) ?
                Long.parseLong(req.queryParams(LIMIT_PROPERTY)) :
                DEFAULT_LIMIT;
        Long receiverId = Long.parseLong(req.queryParams(RECIPIENT_PROPERTY));
        Long startId = Long.parseLong(req.queryParams(START_PROPERTY));

        Collection<Message> messages = messageService.getMessagesByReceiverId(receiverId, startId, limit);

        return JSONUtil.dataToJson(Collections.singletonList(messages));
    };

}
