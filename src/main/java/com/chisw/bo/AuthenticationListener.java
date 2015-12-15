package com.chisw.bo;

/**
 * Created by Nikolas
 */
public interface AuthenticationListener {
    void onAuthenticationSuccess(String string);

    void onAuthenticationFailure(int errorCode);
}
