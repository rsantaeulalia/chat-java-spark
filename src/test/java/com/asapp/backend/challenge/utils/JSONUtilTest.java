package com.asapp.backend.challenge.utils;

import com.asapp.backend.challenge.controller.model.ContentRequest;
import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.controller.model.UserRequest;
import org.junit.Assert;
import org.junit.Test;

public class JSONUtilTest {

    private final String userJson = "{\n" +
            "\"username\": \"TestingUser56\",\n" +
            "\"password\": \"Pa$$word\"\n" +
            "}";

    private final UserRequest userRequest = new UserRequest("TestingUser56", "Pa$$word");

    private final String messageJson = "{\n" +
            "\"recipient\": 1,\n" +
            "\"sender\": 2,\n" +
            " \"content\": {\n" +
            "    \"type\": \"text\",\n" +
            "    \"text\": \"this is a test text\"\n" +
            " }\n" +
            "}";

    private final MessageRequest messageRequest = new MessageRequest(1L, 2L, new ContentRequest("text", "this is a test text", null, 0, 0, null));

    @Test
    public void givenJsonWhenJSONUtilIsCalledReturnClass() {
        Assert.assertTrue(userJson.replaceAll("\\s+", "").equalsIgnoreCase(JSONUtil.dataToJson(userRequest).replaceAll("\\s+", "")));
    }

    @Test
    public void givenClassWhenJSONUtilIsCalledReturnJson() {
        UserRequest returnedUser = JSONUtil.jsonToData(userJson, UserRequest.class);
        Assert.assertEquals(userRequest.getUsername(), returnedUser.getUsername());
        Assert.assertEquals(userRequest.getPassword(), returnedUser.getPassword());
    }

    @Test
    public void givenMessageClassWhenJSONUtilIsCalledReturnJson() {
        MessageRequest returnedMessage = JSONUtil.jsonToData(messageJson, MessageRequest.class);
        Assert.assertEquals(messageRequest.getRecipient(), returnedMessage.getRecipient());
        Assert.assertEquals(messageRequest.getSender(), returnedMessage.getSender());
        Assert.assertEquals(messageRequest.getContent().getText(), returnedMessage.getContent().getText());
        Assert.assertEquals(messageRequest.getContent().getType(), returnedMessage.getContent().getType());
    }
}
