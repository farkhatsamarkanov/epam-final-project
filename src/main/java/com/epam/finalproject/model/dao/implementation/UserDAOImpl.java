package com.epam.finalproject.model.dao.implementation;

import com.epam.finalproject.model.dao.UserDAO;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.model.jdbc.DBConnectionPool;
import com.epam.finalproject.constants.DBConstants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImpl extends AbstractDAO implements UserDAO {
    private DBConstants dbConstants;
    private DBConnectionPool dbConnectionPool;
    private final String SELECT_SPECIFIC_USER_BY_NAME_AND_PASSWORD = "select * from login where user_name=? and password=?";
    private final String SELECT_SPECIFIC_USER_BY_NAME = "select * from login where user_name=?";
    private final String INSERT_USER = "insert into login (user_name, password) values (?, ?)";
    private final String UPDATE_USER = "update login set user_name=?, password=? where id=?";
    private final String DELETE_USER = "delete from login where user_name=?";

    UserDAOImpl() throws IOException, SQLException, ClassNotFoundException {
        dbConstants = DBConstants.getInstance();
        dbConnectionPool = DBConnectionPool.getInstance(dbConstants.getDriver(), dbConstants.getUrl(), dbConstants.getUser(), dbConstants.getPassword(), dbConstants.getPoolSize());
    }

    public DBConnectionPool getDbConnectionPool() {
        return dbConnectionPool;
    }

    @Override
    public boolean isSignedUpUser(String userName, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(SELECT_SPECIFIC_USER_BY_NAME_AND_PASSWORD);
                statement.setString(1, userName);
                statement.setString(2, password);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return true;
                }
            }
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, resultSet);
        }
        return false;
    }

    @Override
    public boolean signUpUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(SELECT_SPECIFIC_USER_BY_NAME);
                statement.setString(1, user.getUserName());
                resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    statement = connection.prepareStatement(INSERT_USER);
                    statement.setString(1, user.getUserName());
                    statement.setString(2, user.getPassword());
                    return statement.executeUpdate() > 0;
                }
            }
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, resultSet);
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(UPDATE_USER);
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());
                statement.setInt(3, user.getId());
                return statement.executeUpdate() > 0;
            }
            return false;
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, null);
        }
    }

    @Override
    public boolean deleteUser(String userName) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(DELETE_USER);
                statement.setString(1, userName);
                return statement.executeUpdate() > 0;
            }
            return false;
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, null);
        }
    }

    @Override
    public User getUser(String userName) throws SQLException {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(SELECT_SPECIFIC_USER_BY_NAME);
                statement.setString(1, userName);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int fetchedId = resultSet.getInt(dbConstants.getId());
                    String fetchedUserName = resultSet.getString(dbConstants.getUserName());
                    String fetchedPassword = resultSet.getString(dbConstants.getUserPassword());
                    user = new User
                            .Builder()
                            .id(fetchedId)
                            .userName(fetchedUserName)
                            .password(fetchedPassword)
                            .build();
                } else {
                    LOG.error("Could not find user: " + userName);
                    throw new SQLException("Could not find user: " + userName);
                }
            }
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, resultSet);
        }
        return user;
    }

}
