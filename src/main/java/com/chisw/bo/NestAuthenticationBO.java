package com.chisw.bo;

import java.util.List;

/**
 * Created by Nikolas
 */

public interface NestAuthenticationBO {
    String FIREBASE_URL = "https://developer-api.nest.com";
    String TERMOSTAT_ID = "9T6aNHbKXmeDb_sTqqsq6SvsXH6c_dQa";
    String NEST_ACCESS_TOKEN_URL = "https://api.home.nest.com/oauth2/access_token?client_id=cbaa7c7f-7674-4920-8416-a952545365c0&code=%AUTHORIZATION_CODE%&client_secret=CFt4VPDScawg1xI7VzERf7cp5&grant_type=authorization_code";

    String getToken(String pinCode);
    List<String> getTermostatList();
}
