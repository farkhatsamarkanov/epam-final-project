package com.epam.finalproject.controller.command.user_command;

import com.epam.finalproject.constants.MappingConstants;
import com.epam.finalproject.constants.RequestParamsConstants;
import com.epam.finalproject.controller.command.CommandStrategy;
import com.epam.finalproject.model.dao.implementation.DAOFactory;
import com.epam.finalproject.model.dao.implementation.UserDAOImpl;
import com.epam.finalproject.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GetUserCommand extends CommandStrategy {
    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        dao = DAOFactory.getDao(DAOFactory.DAOTypes.USER);
        requestParamsConstants = RequestParamsConstants.getInstance();
        mappingConstants = MappingConstants.getInstance();
        String userName = request.getParameter(requestParamsConstants.getUserNameParam());
        User user = ((UserDAOImpl) dao).getUser(userName);
        request.setAttribute(requestParamsConstants.getFetchedUserParam(), user);
        RequestDispatcher dispatcher = request.getRequestDispatcher(mappingConstants.getPathToUserUpdatePage());
        dispatcher.forward(request, response);
    }
}
