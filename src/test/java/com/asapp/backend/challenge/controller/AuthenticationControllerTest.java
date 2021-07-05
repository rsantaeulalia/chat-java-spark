package com.asapp.backend.challenge.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.asapp.backend.challenge.controller.model.UserRequest;
import com.asapp.backend.challenge.controller.utils.HttpConvertUtil;
import com.asapp.backend.challenge.resources.LoginResource;
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

public class AuthenticationControllerTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private final UserRequest userRequest = new UserRequest("username", "password");
    private final LoginResource loginResource = new LoginResource(1L, "token");
    private CloseableHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void loginUserWithPost() throws IOException {
        stubFor(post(Path.LOGIN)
                .withRequestBody(containing(JSONUtil.dataToJson(userRequest)))
                .willReturn(ok()
                        .withBody(JSONUtil.dataToJson(loginResource))));

        HttpPost request = new HttpPost("http://localhost:8089" + Path.LOGIN);
        request.setEntity(new StringEntity(JSONUtil.dataToJson(userRequest)));
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = HttpConvertUtil.convertHttpResponseToString(httpResponse);

        verify(postRequestedFor(urlEqualTo(Path.LOGIN)));
        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(JSONUtil.dataToJson(loginResource), stringResponse);
    }
}
