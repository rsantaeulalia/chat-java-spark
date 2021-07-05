package com.asapp.backend.challenge.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.created;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.controller.utils.HttpConvertUtil;
import com.asapp.backend.challenge.resources.UserResource;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.asapp.backend.challenge.utils.Path;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

public class UserControllerTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private final UserRequest userRequest = new UserRequest("username", "password");
    private final UserResource userResource = new UserResource(1L);
    private CloseableHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void createUserWithPost() throws IOException {
        stubFor(post(Path.USERS)
                .withRequestBody(containing(JSONUtil.dataToJson(userRequest)))
                .willReturn(created()
                        .withBody(JSONUtil.dataToJson(userResource))));

        HttpPost request = new HttpPost("http://localhost:8089" + Path.USERS);
        request.setEntity(new StringEntity(JSONUtil.dataToJson(userRequest)));
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = HttpConvertUtil.convertHttpResponseToString(httpResponse);

        verify(postRequestedFor(urlEqualTo(Path.USERS)));
        Assert.assertEquals(201, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(JSONUtil.dataToJson(userResource), stringResponse);
    }
}
