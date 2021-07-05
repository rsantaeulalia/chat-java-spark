package com.asapp.backend.challenge.model.adapter;

import com.asapp.backend.challenge.controller.model.ContentRequest;
import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.exceptions.ContentTypeNotSupportedException;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.model.Text;
import com.asapp.backend.challenge.model.Video;
import com.asapp.backend.challenge.model.enums.SourceEnum;
import com.asapp.backend.challenge.resources.ContentResource;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.resources.MessageResponseResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class MessageAdapterTest {

    private MessageRequest messageRequest;
    private MessageResource messageResource;
    private MessageResource expectedMessageResource;
    private Message message;
    private Message expectedMessage;
    private MessageResponseResource messageResponseResource;
    private MessageResponseResource expectedMessageResponseResource;

    @Before
    public void setUp() {
        messageRequest = null;
        messageResource = null;
        message = null;
        messageResponseResource = null;
        expectedMessageResponseResource = null;
    }

    @Test
    public void adaptFromApiToDomainModel() {
        givenAMessageRequest();
        whenMessageAdapterToDomainIsCalled();
        thenMessageIsReturned();
    }

    private void givenAMessageRequest() {
        messageRequest = new MessageRequest(1L, 2L, new ContentRequest("video", null, "https://videourl.com",
                0, 0, "youtube"));
        expectedMessage = new Message(2L, 1L, new Video("video", "https://videourl.com", SourceEnum.YOUTUBE), new Date());
    }

    private void whenMessageAdapterToDomainIsCalled() {
        message = MessageAdapter.toDomain(messageRequest);
    }

    private void thenMessageIsReturned() {
        Assert.assertNotNull(message);
        Assert.assertEquals(message.getSenderId(), expectedMessage.getSenderId());
        Assert.assertEquals(message.getReceiverId(), expectedMessage.getReceiverId());
        Assert.assertTrue(message.getContent() instanceof Video);
        Assert.assertEquals(((Video) message.getContent()).getSource(), ((Video) expectedMessage.getContent()).getSource());
        Assert.assertEquals(((Video) message.getContent()).getUrl(), ((Video) expectedMessage.getContent()).getUrl());
    }

    @Test
    public void adaptFromDomainToApiModel() {
        givenAMessage();
        whenMessageAdapterToApiIsCalled();
        thenMessageResourceIsReturned();
    }

    private void givenAMessage() {
        message = new Message(1L, 2L, 1L, new Text("text", "Testing text"), new Date());
        expectedMessageResource = new MessageResource(1L, new Date(), 2L, 1L, new ContentResource("text", "Testing text"));
        expectedMessageResponseResource = new MessageResponseResource(1L, new Date());
    }

    private void whenMessageAdapterToApiIsCalled() {
        messageResource = MessageAdapter.toApi(message);
    }

    private void thenMessageResourceIsReturned() {
        Assert.assertNotNull(messageResource);
        Assert.assertEquals(messageResource.getSender(), expectedMessageResource.getSender());
        Assert.assertEquals(messageResource.getRecipient(), expectedMessageResource.getRecipient());
        Assert.assertEquals(messageResource.getContent().getText(), expectedMessageResource.getContent().getText());
        Assert.assertEquals(messageResource.getContent().getUrl(), expectedMessageResource.getContent().getUrl());
    }

    @Test
    public void adaptFromDomainToApiResponseModel() {
        givenAMessage();
        whenMessageAdapterToApiResponseIsCalled();
        thenMessageResponseResourceIsReturned();
    }

    private void whenMessageAdapterToApiResponseIsCalled() {
        messageResponseResource = MessageAdapter.toMessageResponse(message);
    }

    private void thenMessageResponseResourceIsReturned() {
        Assert.assertNotNull(messageResponseResource);
        Assert.assertEquals(messageResponseResource.getId(), expectedMessageResponseResource.getId());
    }

    @Test(expected = ContentTypeNotSupportedException.class)
    public void adaptFromDomainToApiModelWithWrongType() {
        givenAWrongTypeContentMessage();
        whenMessageAdapterToDomainIsCalled();
    }

    private void givenAWrongTypeContentMessage() {
        messageRequest = new MessageRequest(1L, 2L, new ContentRequest("wrongtype", null, "https://videourl.com",
                0, 0, "youtube"));
    }
}
