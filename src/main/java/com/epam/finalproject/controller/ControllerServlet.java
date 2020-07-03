package com.epam.finalproject.controller;

import com.epam.finalproject.controller.command.CommandExecutor;
import com.epam.finalproject.model.jdbc.DBConnectionPool;
import com.epam.finalproject.constants.DBConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main servlet that handles all requests using strategy pattern (CommandStrategy class)
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private final static Logger LOG = LogManager.getLogger("ControllerServletLogger");
    private CommandExecutor commandExecutor = null;

    @Override
    public void init() throws ServletException {
        commandExecutor = new CommandExecutor();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        LOG.info("Processing request...");
        try {
            commandExecutor.executeCommand(request, response);
        } catch (ClassNotFoundException e) {
            LOG.error(e);
        } catch (SQLException e) {
            LOG.error(e);
        } catch (IOException e) {
            LOG.error("Failed to read config file: " + e);
        }
    }

    @Override
    public void destroy() {
        try {
            DBConstants dbConstants = DBConstants.getInstance();
            DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance(dbConstants.getDriver(), dbConstants.getUrl(), dbConstants.getUser(), dbConstants.getPassword(), dbConstants.getPoolSize());
            dbConnectionPool.destroyPool();
        } catch (IOException e) {
            LOG.error(e);
        } catch (SQLException e) {
            LOG.error(e);
        } catch (ClassNotFoundException e) {
            LOG.error(e);
        }
    }
}
