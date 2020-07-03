package com.epam.finalproject.model.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public final class DBConnectionPool {
    private final static Logger LOG = LogManager.getLogger("ConnectionPoolLogger");
    private String driver, url, user, password;
    private static DBConnectionPool instance = null;
    private static BlockingQueue<Connection> connections;

    private DBConnectionPool(String driver, String url, String user, String password, Integer poolSize) throws ClassNotFoundException, IOException, SQLException {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        connections = new ArrayBlockingQueue<>(poolSize);
        Class.forName(driver);
        for (int i = 0; i < poolSize; i++) {
            connections.add(DriverManager.getConnection(url, user, password));
        }
    }

    public static DBConnectionPool getInstance(String driver, String url, String user, String password, Integer poolSize) throws SQLException, IOException, ClassNotFoundException {
        if (instance == null)
            instance = new DBConnectionPool(driver, url, user, password, poolSize);
        return instance;
    }

    public Connection getConnection() {
        try {
            return connections.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOG.error("Failed to poll connection from connection pool: " + e);
            return null;
        }
    }

    public void putConnection(Connection connection) {
        if (connection != null)
            connections.add(connection);
    }

    public void destroyPool() {
        for (Connection connection : connections) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOG.error("Failed to close connection: " + e);
                }
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        while (DriverManager.getDrivers().hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            } catch (SQLException e) {
                LOG.error("Failed to deregister DB driver: " + e);
            }
        }
    }

}
