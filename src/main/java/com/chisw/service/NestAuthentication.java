package com.chisw.service;

import com.chisw.bo.AuthenticationListener;
import com.chisw.bo.EventListener;
import com.chisw.bo.NestAuthenticationBO;
import com.firebase.client.*;

import java.util.List;

/**
 * Created by Nikolas
 */

public class NestAuthentication implements NestAuthenticationBO {

    private Firebase fireBase;
    private static NestAuthentication nestAuthentication = null;

    private boolean authenticated = false;

    private NestAuthentication(String firebaseURL) {
        fireBase = new Firebase(firebaseURL);
    }

    public static NestAuthentication getInstance(String firebaseURL) {
        if (nestAuthentication == null) {
            nestAuthentication = new NestAuthentication(firebaseURL);
        }
        return nestAuthentication;
    }

    public void authenticate(String appToken, AuthenticationListener listener) {
        fireBase.authWithCustomToken(appToken, new NestFirebaseAuthListener(listener));
    }

    public void setDeviceParam(String deviceType, String deviceId, String parameter, Object value) {
        fireBase.child("devices").child(deviceType).child(deviceId).child(parameter).setValue(value);
    }

    public void getDeviceParam(String deviceType, String deviceId, String parameter, EventListener eventListener) {
        fireBase.child("devices").child(deviceType).child(deviceId).child(parameter)
                .addValueEventListener(new NestValueEventListener(eventListener));
    }

    public String getToken(String pinCode) {
        return null;
    }

    public List<String> getTermostatList() {
        return null;
    }

    private class NestFirebaseAuthListener implements Firebase.AuthResultHandler {
        private AuthenticationListener mListener;

        public NestFirebaseAuthListener(AuthenticationListener listener) {
            mListener = listener;
        }

        public void onAuthenticated(AuthData arg0) {
            mListener.onAuthenticationSuccess(arg0.toString());

        }

        public void onAuthenticationError(FirebaseError arg0) {
            mListener.onAuthenticationFailure(arg0.getCode());
        }
    }

    private class NestValueEventListener implements ValueEventListener {
        private EventListener mListener;

        public NestValueEventListener(EventListener listener) {
            mListener = listener;
        }

        public void onCancelled(FirebaseError arg0) {
            mListener.onCancelled(arg0.getCode());
        }

        public void onDataChange(DataSnapshot arg0) {
            mListener.onDataChange(arg0.getValue().toString());
        }
    }
}
