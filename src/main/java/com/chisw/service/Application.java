package com.chisw.service;

import com.chisw.bo.AuthenticationListener;
import com.chisw.bo.NestAuthenticationBO;
import com.chisw.dto.TokenResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nikolas
 */
public class Application implements AuthenticationListener {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);
    private NestAuthentication nestAuthentication;

    public void start(String pin) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(NestAuthenticationBO.NEST_ACCESS_TOKEN_URL.replaceAll("%AUTHORIZATION_CODE%", pin.trim()));
        HttpResponse response = client.execute(post);
        TokenResult result = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()), TokenResult.class);

        logger.info(result.toString());

        initApplication(result.getAccessToken());
    }

    private void initApplication(String appToken) {
        logger.info("Authentication...");

        nestAuthentication = NestAuthentication.getInstance(NestAuthenticationBO.FIREBASE_URL);
        nestAuthentication.authenticate(appToken, this);

        logger.info("Authentication complete");
    }

    public NestAuthentication getNestAuthentication() {
        return nestAuthentication;
    }

    public void onAuthenticationSuccess(String string) {
        logger.info("Auth was success. System ready for use.");
    }

    public void onAuthenticationFailure(int errorCode) {
        logger.info("Auth was failed. Code: " + errorCode);
    }
}
