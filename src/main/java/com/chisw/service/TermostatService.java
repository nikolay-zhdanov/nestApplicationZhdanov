package com.chisw.service;

import com.chisw.bo.EventListener;
import com.chisw.bo.TermostatServiceBO;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nikolas
 */
public class TermostatService implements TermostatServiceBO {
    private static final Logger LOGGER = Logger.getLogger(TermostatService.class.getName());
    private NestAuthentication nestAuthentication;

    public TermostatService(NestAuthentication nestAuthentication) {
        this.nestAuthentication = nestAuthentication;
    }

    public void enableTermostatFan(String termostatId) {
        nestAuthentication.setDeviceParam("thermostats", termostatId, "fan_timer_active", true);
    }

    public void disableTermostatFan(String termostatId) {
        nestAuthentication.setDeviceParam("thermostats", termostatId, "fan_timer_active", false);
    }

    public void enableTermostatFanStatusListener(String termostatId) {
        nestAuthentication.getDeviceParam("thermostats", termostatId, "fan_timer_active", new DefaultValueListener("Fan status"));
        nestAuthentication.getDeviceParam("thermostats", termostatId, "target_temperature_high_c", new DefaultValueListener("Temperature status"));
        nestAuthentication.getDeviceParam("thermostats", termostatId, "target_temperature_low_c", new DefaultValueListener("Temperature status"));
    }

    public void setLowTermostatTemperature(String termostatId, float temp) {
        nestAuthentication.setDeviceParam("thermostats", termostatId, "target_temperature_low_c", temp);
    }

    public void setHighTermostatTemperature(String termostatId, float temp) {
        nestAuthentication.setDeviceParam("thermostats", termostatId, "target_temperature_high_c", temp);
    }


    private class DefaultValueListener implements EventListener {

        private String type;

        public DefaultValueListener(String type) {
            this.type = type;
        }

        public void onDataChange(String value) {
            LOGGER.log(Level.INFO, type + ": " + value);

        }

        public void onCancelled(int errorCode) {
            LOGGER.log(Level.SEVERE, "Error code: " + errorCode);
        }
    }
}
