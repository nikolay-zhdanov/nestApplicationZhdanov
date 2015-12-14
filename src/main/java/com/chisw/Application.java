package com.chisw;

import com.chisw.bo.AuthenticationListener;
import com.chisw.bo.NestAuthenticationBO;
import com.chisw.dto.TokenResult;
import com.chisw.service.NestAuthentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nikolas
 */
public class Application implements AuthenticationListener {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private NestAuthentication nestAuthentication;

    public void start(String pin) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(NestAuthenticationBO.NEST_ACCESS_TOKEN_URL.replaceAll("%AUTHORIZATION_CODE%", pin.trim()));
        HttpResponse response = client.execute(post);
        TokenResult result = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()), TokenResult.class);

        LOGGER.log(Level.INFO, result.toString());

        initApplication(result.getAccessToken());
    }

    private void initApplication(String appToken) {
        LOGGER.log(Level.INFO, "Authentication...");

        nestAuthentication = NestAuthentication.getInstance(NestAuthenticationBO.FIREBASE_URL);
        nestAuthentication.authenticate(appToken, this);

        LOGGER.log(Level.INFO, "Authentication complete");
    }

    public NestAuthentication getNestAuthentication() {
        return nestAuthentication;
    }

    public void onAuthenticationSuccess(String string) {
        LOGGER.log(Level.INFO, "Auth was success. System ready for use.");
    }

    public void onAuthenticationFailure(int errorCode) {
        LOGGER.log(Level.INFO, "Auth was failed. Code: " + errorCode);
    }
}
