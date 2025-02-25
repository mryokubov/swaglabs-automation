package com.swaglas.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String PATH = "src/main/resources/config.properties";
    private ConfigReader(){}

    static {
        try {
            FileInputStream inputStream = new FileInputStream(PATH);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String keyName){
        return properties.getProperty(keyName);
    }
}
