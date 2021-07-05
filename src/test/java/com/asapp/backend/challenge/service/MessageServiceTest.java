package com.asapp.backend.challenge.service;

import static org.easymock.EasyMock.anyLong;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import com.asapp.backend.challenge.exceptions.UserNotFoundException;
import com.asapp.backend.challenge.model.Image;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.model.User;
import com.asapp.backend.challenge.model.Video;
import com.asapp.backend.challenge.model.enums.SourceEnum;
import com.asapp.backend.challenge.repository.MessageRepository;
import com.asapp.backend.challenge.repository.UserRepository;
import com.asapp.backend.challenge.service.implementation.MessageServiceImpl;
import com.asapp.backend.challenge.service.implementation.UserServiceImpl;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MessageServiceTest {

    private final Message message = new Message(2L, 1L, new Video("video", "https://videourl.com", SourceEnum.YOUTUBE, null), new Date());
    private final Message expectedMessage = new Message(1L, 2L, 1L, new Video("video", "https://videourl.com", SourceEnum.YOUTUBE, null), new Date());
    private final Message expectedMessage2 = new Message(2L, 2L, 1L, new Image("image", "https://videourl.com", 100, 200, null), new Date());

    private final User user = new User(1L, "username", "password", new Date());

    private MessageService messageService;
    private UserService userService;
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        messageRepository = EasyMock.createMock(MessageRepository.class);
        userRepository = EasyMock.createMock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        messageService = new MessageServiceImpl(messageRepository, userService);
    }

    @Test
    public void givenAMessageWhenServiceSaveMethodIsCalledThenReturnMessage() {
        expect(messageRepository.addMessage(message)).andReturn(expectedMessage);

        replay(messageRepository);

        Message savedMessage = messageService.saveMessage(message);

        Assert.assertEquals(savedMessage.getId(), expectedMessage.getId());

        verify(messageRepository);
    }

    @Test
    public void givenQueryParamsWhenGetMessagesIsCalledThenReturnCollectionOfMessages() {
        expect(userRepository.getById(anyLong())).andReturn(Optional.of(user));
        expect(messageRepository.getMessages(1L, 1L, 2L)).andReturn(List.of(expectedMessage, expectedMessage2));

        replay(messageRepository, userRepository);

        Collection<Message> savedMessage = messageService.getMessagesByReceiverId(1L, 1L, 2L);

        Assert.assertEquals(savedMessage.size(), 2);

        verify(messageRepository, userRepository);
    }

    @Test(expected = UserNotFoundException.class)
    public void givenQueryParamsWithWrongIdUserWhenGetMessagesIsCalledThenThrowException() {
        expect(userRepository.getById(anyLong())).andReturn(Optional.empty());
        expect(messageRepository.getMessages(1L, 1L, 2L)).andReturn(List.of(expectedMessage, expectedMessage2));

        replay(messageRepository, userRepository);

        messageService.getMessagesByReceiverId(1L, 1L, 2L);
        verify(messageRepository, userRepository);
    }
}
