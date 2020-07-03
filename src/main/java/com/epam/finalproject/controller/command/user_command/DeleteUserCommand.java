package com.epam.finalproject.controller.command.user_command;

import com.epam.finalproject.constants.RequestParamsConstants;
import com.epam.finalproject.controller.command.CommandStrategy;
import com.epam.finalproject.model.dao.implementation.DAOFactory;
import com.epam.finalproject.model.dao.implementation.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserCommand extends CommandStrategy {
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        requestParamsConstants = RequestParamsConstants.getInstance();
        dao = DAOFactory.getDao(DAOFactory.DAOTypes.USER);
        ((UserDAOImpl) dao).deleteUser(request.getParameter(requestParamsConstants.getUserNameParam()));
    }
}
