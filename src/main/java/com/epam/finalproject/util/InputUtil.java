package com.epam.finalproject.util;

public class InputUtil {
    private final String LOGIN;
    private final String PASSWORD;

    public InputUtil() {
        LOGIN = "^[a-zA-Z_0-9]{3,45}$";
        PASSWORD = "^[a-zA-Z_!@#%0-9]{8,16}$";
    }

    public boolean isValidUserName(String userName) {
        return notNullOrEmpty(userName) && userName.matches(LOGIN);
    }

    public boolean isValidPassword(String password) {
        return notNullOrEmpty(password) && password.matches(PASSWORD);
    }

    private boolean notNullOrEmpty(String string) {
        return string != null && !string.trim().isEmpty();
    }


}
