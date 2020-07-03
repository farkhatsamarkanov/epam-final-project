package com.epam.finalproject.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class MappingConstants {
    private static MappingConstants instance = null;
    private static String pathToSignInPage = null;
    private static String pathToSignUpPage = null;
    private static String pathToListCommand = null;
    private static String pathToListPage = null;
    private static String pathToUpdatePage = null;
    private static String pathToUserUpdatePage = null;

    private MappingConstants() throws IOException {
        InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("mapping.properties");
        Properties properties = new Properties();
        if (file != null) {
            properties.load(file);
            pathToSignInPage = properties.getProperty("pathToSignInPage");
            pathToSignUpPage = properties.getProperty("pathToSignUpPage");
            pathToListCommand = properties.getProperty("pathToListCommand");
            pathToListPage = properties.getProperty("pathToListPage");
            pathToUpdatePage = properties.getProperty("pathToUpdatePage");
            pathToUserUpdatePage = properties.getProperty("pathToUserUpdatePage");
        }
    }

    public static String getPathToSignInPage() {
        return pathToSignInPage;
    }

    public static String getPathToSignUpPage() {
        return pathToSignUpPage;
    }

    public static String getPathToListCommand() {
        return pathToListCommand;
    }

    public static String getPathToListPage() {
        return pathToListPage;
    }

    public static String getPathToUpdatePage() {
        return pathToUpdatePage;
    }

    public static String getPathToUserUpdatePage() {
        return pathToUserUpdatePage;
    }

    public static MappingConstants getInstance() throws IOException {
        if (instance == null)
            instance = new MappingConstants();
        return instance;
    }
}
