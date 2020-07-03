package com.epam.finalproject.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class RequestParamsConstants {
    private static RequestParamsConstants instance = null;
    private static String userNameParam = null;
    private static String passwordParam = null;
    private static String firstNameParam = null;
    private static String lastNameParam = null;
    private static String emailParam = null;
    private static String studentIdParam = null;
    private static String studentListParam = null;
    private static String fetchedStudentParam = null;
    private static String searchBoxContentParam = null;
    private static String confirmedPasswordParam = null;
    private static String userIdParam = null;
    private static String fetchedUserParam = null;
    private static String oldPasswordParam = null;
    private static String newPasswordParam = null;
    private static String confirmedNewPasswordParam = null;


    private RequestParamsConstants() throws IOException {
        InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("request_params.properties");
        Properties properties = new Properties();
        if (file != null) {
            properties.load(file);
            userNameParam = properties.getProperty("userNameParam");
            passwordParam = properties.getProperty("passwordParam");
            firstNameParam = properties.getProperty("firstNameParam");
            lastNameParam = properties.getProperty("lastNameParam");
            emailParam = properties.getProperty("emailParam");
            studentIdParam = properties.getProperty("studentIdParam");
            studentListParam = properties.getProperty("studentListParam");
            fetchedStudentParam = properties.getProperty("fetchedStudentParam");
            searchBoxContentParam = properties.getProperty("searchBoxContentParam");
            confirmedPasswordParam = properties.getProperty("confirmedPasswordParam");
            userIdParam = properties.getProperty("userIdParam");
            fetchedUserParam = properties.getProperty("fetchedUserParam");
            oldPasswordParam = properties.getProperty("oldPasswordParam");
            newPasswordParam = properties.getProperty("newPasswordParam");
            confirmedNewPasswordParam = properties.getProperty("confirmedNewPasswordParam");
        }
    }

    public static String getUserNameParam() {
        return userNameParam;
    }

    public static String getPasswordParam() {
        return passwordParam;
    }

    public static String getFirstNameParam() {
        return firstNameParam;
    }

    public static String getLastNameParam() {
        return lastNameParam;
    }

    public static String getEmailParam() {
        return emailParam;
    }

    public static String getStudentIdParam() {
        return studentIdParam;
    }

    public static String getStudentListParam() {
        return studentListParam;
    }

    public static String getFetchedStudentParam() {
        return fetchedStudentParam;
    }

    public static String getSearchBoxContentParam() {
        return searchBoxContentParam;
    }

    public static String getConfirmedPasswordParam() {
        return confirmedPasswordParam;
    }

    public static String getUserIdParam() {
        return userIdParam;
    }

    public static String getFetchedUserParam() {
        return fetchedUserParam;
    }

    public static String getOldPasswordParam() {
        return oldPasswordParam;
    }

    public static String getNewPasswordParam() {
        return newPasswordParam;
    }

    public static String getConfirmedNewPasswordParam() {
        return confirmedNewPasswordParam;
    }

    public static RequestParamsConstants getInstance() throws IOException {
        if (instance == null)
            instance = new RequestParamsConstants();
        return instance;
    }
}
