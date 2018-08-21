package com.jtthink.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParamUtil {

    private static String CONGFIG_URL;
    private static final Properties properties = new Properties();
    static{
        try {
            CONGFIG_URL = "application.properties";
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONGFIG_URL);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigValue(String key){
        return properties.getProperty(key);
    }



}
