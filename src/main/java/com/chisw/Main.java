package com.chisw;

import com.chisw.bo.TermostatServiceBO;
import com.chisw.service.NestAuthentication;
import com.chisw.service.TermostatService;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nicolas
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "App started...");

        try {
            Application application = new Application();
            application.start(args[0]);
            TermostatServiceBO termostatService = new TermostatService(application.getNestAuthentication());
            while (true){
                Scanner scanner = new Scanner(System.in);
                String command = scanner.next().toLowerCase();
                if (command.equals("exit")) {
                    System.exit(0);
                } else if (command.equals("fan_on")) {
                    termostatService.enableTermostatFan(NestAuthentication.TERMOSTAT_ID);
                } else if (command.equals("fan_off")) {
                    termostatService.disableTermostatFan(NestAuthentication.TERMOSTAT_ID);
                } else if (command.contains("set_low_temp")) {
                    termostatService.setLowTermostatTemperature(NestAuthentication.TERMOSTAT_ID, new Float(command.substring(command.length() - 4)));
                } else if (command.contains("set_high_temp")) {
                    termostatService.setHighTermostatTemperature(NestAuthentication.TERMOSTAT_ID, new Float(command.substring(command.length() - 4)));
                }

                termostatService.enableTermostatFanStatusListener(NestAuthentication.TERMOSTAT_ID);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error");
        }
    }
}
