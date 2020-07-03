package com.epam.finalproject.model.dao;

import com.epam.finalproject.model.entity.User;

import java.sql.SQLException;

public interface UserDAO {
    boolean isSignedUpUser(String userName, String password) throws SQLException;

    boolean signUpUser(User user) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean deleteUser(String userId) throws SQLException;

    User getUser(String userName) throws SQLException;
}