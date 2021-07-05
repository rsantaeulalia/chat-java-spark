package com.asapp.backend.challenge.repository;

import com.asapp.backend.challenge.model.ContentFactory;
import com.asapp.backend.challenge.model.Message;
import com.asapp.backend.challenge.model.Video;
import com.asapp.backend.challenge.repository.SqLiteImplementation.MessageSqlLiteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Collection;
import java.util.Date;

public class MessageRepositoryTest {
    private static final Sql2o sql2o = new Sql2o("jdbc:sqlite:sample-test.db", null, null);

    private Message messageToSave;
    private Message savedMessage;
    private Collection<Message> foundMessages;
    private MessageRepository messageRepository;

    private Long limit, startId, receiverId;

    @Before
    public void setUp() {
        dropMessageTable();
        messageRepository = new MessageSqlLiteRepository(sql2o);
        messageToSave = null;
        savedMessage = null;
        limit = 0L;
        startId = 0L;
        receiverId = 0L;
    }

    @Test
    public void testSaveMessage() {
        givenMessageToSave();
        whenTheRepositoryIsCalledToSave();
        thenTheMessageIsCorrectlySaved();
    }

    private void givenMessageToSave() {
        messageToSave = new Message(1L, 2L,
                new ContentFactory().create("video", "https://testingvideourl.com", 0, 0,
                        null,
                        "vimeo"),
                new Date());
    }

    private void whenTheRepositoryIsCalledToSave() {
        savedMessage = messageRepository.addMessage(messageToSave);
    }

    private void thenTheMessageIsCorrectlySaved() {
        Assert.assertNotNull(savedMessage);
        Assert.assertEquals(1, savedMessage.getId().longValue());
        Assert.assertEquals(messageToSave.getContent(), savedMessage.getContent());
        Assert.assertTrue(messageToSave.getContent() instanceof Video);
        Assert.assertEquals(messageToSave.getCreationDate(), savedMessage.getCreationDate());
        Assert.assertEquals(messageToSave.getReceiverId(), savedMessage.getReceiverId());
        Assert.assertEquals(messageToSave.getSenderId(), savedMessage.getSenderId());
    }

    @Test
    public void testGetMessagesByQuery() {
        givenMessagesToFind(3L, 2L, 1L);
        whenTheRepositoryFindByParams();
        thenTheUserIsFound();
    }

    private void givenMessagesToFind(Long receiver, Long start, Long limitRows) {
        messageRepository.addMessage(new Message(1L, 2L,
                new ContentFactory().create("video", "https://testingvideourl.com", 0, 0,
                        null,
                        "vimeo"),
                new Date()));
        messageRepository.addMessage(new Message(1L, 2L,
                new ContentFactory().create("image", "https://testingimageurl.com", 100, 300,
                        null,
                        null),
                new Date()));
        messageRepository.addMessage(new Message(1L, 2L,
                new ContentFactory().create("text", null, 0, 0,
                        "text test",
                        null),
                new Date()));
        messageRepository.addMessage(new Message(1L, 3L,
                new ContentFactory().create("video", "https://testingvideourl.com", 0, 0,
                        null,
                        "vimeo"),
                new Date()));
        messageRepository.addMessage(new Message(2L, 4L,
                new ContentFactory().create("video", "https://testingvideourl.com", 0, 0,
                        null,
                        "vimeo"),
                new Date()));

        limit = limitRows;
        startId = start;
        receiverId = receiver;
    }

    private void whenTheRepositoryFindByParams() {
        foundMessages = messageRepository.getMessages(receiverId, startId, limit);
    }

    private void thenTheUserIsFound() {
        Assert.assertEquals(1, foundMessages.size());
        Message message = foundMessages.stream().findFirst().get();
        Assert.assertEquals(message.getReceiverId(), receiverId);
        Assert.assertTrue(message.getId() >= startId);
    }

    @Test
    public void testGetMessagesByQueryLimits() {
        givenMessagesToFind(2L, 1L, 3L);
        whenTheRepositoryFindByParams();
        thenTheMessagesSizeIsThree();
    }

    private void thenTheMessagesSizeIsThree() {
        Assert.assertEquals(3, foundMessages.size());
        Assert.assertTrue(foundMessages.stream().allMatch(message1 -> message1.getId() >= startId));
        Assert.assertTrue(foundMessages.stream().allMatch(message1 -> message1.getReceiverId().equals(receiverId)));
    }

    private static void dropMessageTable() {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("DROP TABLE IF EXISTS MESSAGES").executeUpdate();
        }
    }
}
