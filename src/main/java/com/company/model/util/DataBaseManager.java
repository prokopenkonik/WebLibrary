package com.company.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseManager {

    private static Properties properties;

    static {
        try (InputStream is = SqlQueriesManager.class
                .getClassLoader().getResourceAsStream("db.properties")) {
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataBaseManager() {
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }
}
