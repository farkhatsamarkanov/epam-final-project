package com.epam.finalproject.controller.command.app_command;

import com.epam.finalproject.controller.command.CommandStrategy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeLocaleCommand extends CommandStrategy {

    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        String locale = request.getParameter("LOCALE");
        request.getSession().setAttribute("LOCALE", locale);
        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getParameter("PAGE"));
        dispatcher.forward(request, response);
    }
}
