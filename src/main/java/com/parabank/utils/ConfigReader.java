package com.parabank.utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            // Load file from classpath (src/test/resources)
            InputStream input = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config/config.properties");

            if (input != null) {
                properties.load(input);
                input.close();
            } else {
                System.out.println("ERROR: config.properties file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get property value by key name
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}