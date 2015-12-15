package com.chisw.bo;

/**
 * Created by Nikolas
 */
public interface EventListener {
    void onDataChange(String value);

    void onCancelled(int errorCode);
}
