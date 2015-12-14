package com.chisw.bo;

/**
 * Created by Nikolas
 */
public interface TermostatServiceBO {
    void enableTermostatFan(String termostatId);
    void disableTermostatFan(String termostatId);
    void enableTermostatFanStatusListener(String termostatId);
    void setLowTermostatTemperature(String termostatId, float temp);
    void setHighTermostatTemperature(String termostatId, float temp);
}
