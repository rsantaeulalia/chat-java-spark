package com.asapp.backend.challenge.controller;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.resources.MessageResponseResource;
import com.asapp.backend.challenge.service.MessageService;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collections;

public class MessagesController {

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
        // TODO: Retrieve list of Messages
        return JSONUtil.dataToJson(Collections.singletonList(new MessageResource()));
    };

}
