package com.epam.finalproject.model.entity;

public class User {
    private final int id;
    private final String userName;
    private final String password;

    public static class Builder {
        private int id = 0;
        private String userName = "";
        private String password = "";

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


    private User(Builder builder) {
        id = builder.id;
        userName = builder.userName;
        password = builder.password;
    }


    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
