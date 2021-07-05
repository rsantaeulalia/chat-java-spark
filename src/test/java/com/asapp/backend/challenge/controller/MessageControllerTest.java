package com.asapp.backend.challenge.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.created;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import com.asapp.backend.challenge.controller.model.ContentRequest;
import com.asapp.backend.challenge.controller.model.MessageRequest;
import com.asapp.backend.challenge.controller.utils.HttpConvertUtil;
import com.asapp.backend.challenge.resources.ContentResource;
import com.asapp.backend.challenge.resources.MessageResource;
import com.asapp.backend.challenge.resources.MessageResponseResource;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.asapp.backend.challenge.utils.Path;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MessageControllerTest {
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN = "Bearer token";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private final MessageRequest messageRequest = new MessageRequest(1L, 2L, new ContentRequest("video", null, "https://videourl.com",
            0, 0, "youtube"));
    private final MessageResponseResource messageResponseResource = new MessageResponseResource(1L, new Date());
    private final MessageResource expectedMessageResource1 = new MessageResource(1L, new Date(), 2L, 4L, new ContentResource("text", "Testing text"));
    private final MessageResource expectedMessageResource2 = new MessageResource(2L, new Date(), 2L, 4L, new ContentResource("video", "http://videourl.com", 100, 250));

    private CloseableHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void createMessageWithPost() throws IOException {
        stubFor(post(Path.MESSAGES)
                .withRequestBody(containing(JSONUtil.dataToJson(messageRequest)))
                .withHeader(AUTHORIZATION, containing(TOKEN))
                .willReturn(created()
                        .withBody(JSONUtil.dataToJson(messageResponseResource))));

        HttpPost request = new HttpPost("http://localhost:8089" + Path.MESSAGES);
        request.setEntity(new StringEntity(JSONUtil.dataToJson(messageRequest)));
        request.setHeader(AUTHORIZATION, TOKEN);
        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = HttpConvertUtil.convertHttpResponseToString(httpResponse);

        verify(postRequestedFor(urlEqualTo(Path.MESSAGES)));
        Assert.assertEquals(201, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(JSONUtil.dataToJson(messageResponseResource), stringResponse);
    }

    @Test
    public void getMessageWithParams() throws IOException, URISyntaxException {
        stubFor(get(urlMatching(Path.MESSAGES + ".*"))
                .withQueryParams(Map.of("limit", equalTo("2"),
                        "recipient", equalTo("4"),
                        "start", equalTo("2")))
                .withHeader(AUTHORIZATION, containing(TOKEN))
                .willReturn(ok()
                        .withBody(JSONUtil.dataToJson(List.of(expectedMessageResource1, expectedMessageResource2)))));

        HttpGet request = new HttpGet("http://localhost:8089" + Path.MESSAGES);
        request.setHeader(AUTHORIZATION, TOKEN);
        URI uri = new URIBuilder(request.getURI())
                .addParameter("limit", "2")
                .addParameter("recipient", "4")
                .addParameter("start", "2")
                .build();
        request.setURI(uri);

        HttpResponse httpResponse = httpClient.execute(request);
        String stringResponse = HttpConvertUtil.convertHttpResponseToString(httpResponse);

        verify(getRequestedFor(urlMatching(Path.MESSAGES + ".*")));
        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals(JSONUtil.dataToJson(List.of(expectedMessageResource1, expectedMessageResource2)), stringResponse);
    }
}
