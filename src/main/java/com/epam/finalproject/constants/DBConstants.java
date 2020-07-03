package com.epam.finalproject.constants;

import java.io.*;
import java.util.Properties;

//Singleton
public final class DBConstants {
    private static DBConstants instance = null;
    private static String driver = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String firstName = null;
    private static String lastName = null;
    private static String email = null;
    private static String id = null;
    private static Integer poolSize = null;
    private static String userName = null;
    private static String userPassword = null;

    private DBConstants() throws IOException {
        InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbconfig.properties");
        Properties properties = new Properties();
        if (file != null) {
            properties.load(file);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            firstName = properties.getProperty("firstName");
            lastName = properties.getProperty("lastName");
            email = properties.getProperty("email");
            id = properties.getProperty("id");
            poolSize = Integer.parseInt(properties.getProperty("poolSize"));
            userName = properties.getProperty("userName");
            userPassword = properties.getProperty("userPassword");
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPoolSize() {
        return poolSize;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    static public DBConstants getInstance() throws IOException {
        if (instance == null) {
            instance = new DBConstants();
        }
        return instance;
    }

}
