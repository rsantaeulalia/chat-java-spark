package com.asapp.backend.challenge.controller;

import static java.util.stream.Collectors.toList;

import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.exceptions.MissingParametersException;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.model.adapter.MessageAdapter;
import com.asapp.backend.challenge.service.MessageService;
import com.asapp.backend.challenge.utils.JSONUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collection;

public class MessagesController {

    private static final Long DEFAULT_LIMIT = 10L;
    public static final String START_PROPERTY = "start";
    public static final String LIMIT_PROPERTY = "limit";
    public static final String RECIPIENT_PROPERTY = "recipient";

    private MessageService messageService;

    public MessagesController(final MessageService messageService) {
        this.messageService = messageService;
    }

    public Route sendMessage = (Request req, Response resp) -> {
        MessageRequest messageRequest = JSONUtil.jsonToData(req.body(), MessageRequest.class);
        Message message = messageService.saveMessage(MessageAdapter.toDomain(messageRequest));
        resp.status(201);
        return JSONUtil.dataToJson(MessageAdapter.toMessageResponse(message));
    };

    public Route getMessages = (Request req, Response rep) -> {
        Long limit, receiverId, startId;
        try {
            limit = req.queryParams().contains(LIMIT_PROPERTY) ?
                    Long.parseLong(req.queryParams(LIMIT_PROPERTY)) :
                    DEFAULT_LIMIT;
            receiverId = Long.parseLong(req.queryParams(RECIPIENT_PROPERTY));
            startId = Long.parseLong(req.queryParams(START_PROPERTY));
        } catch (Exception e) {
            throw new MissingParametersException("Required parameters must be present");
        }

        Collection<Message> messages = messageService.getMessagesByReceiverId(receiverId, startId, limit);

        return JSONUtil.dataToJson(messages.stream().map(MessageAdapter::toApi).collect(toList()));
    };


}
