package com.chisw.service;

import com.chisw.bo.EventListener;
import com.chisw.bo.TermostatServiceBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

/**
 * Created by Nikolas
 */
public class TermostatService implements TermostatServiceBO {
    private final Logger logger = LoggerFactory.getLogger(TermostatService.class);
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
//            logger.info(type + ": " + value);
            logger.info(value);

        }

        public void onCancelled(int errorCode) {
            logger.info("Error code: " + errorCode);
        }
    }
}
