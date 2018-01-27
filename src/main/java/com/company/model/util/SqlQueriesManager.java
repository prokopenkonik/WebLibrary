package com.company.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SqlQueriesManager {

    private static Properties properties;

    static {
        try (InputStream is = SqlQueriesManager.class
                .getClassLoader().getResourceAsStream("sql/queries.properties")) {
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SqlQueriesManager() {
    }

    public static String getQuery(String key) {
        return properties.getProperty(key);
    }
}
