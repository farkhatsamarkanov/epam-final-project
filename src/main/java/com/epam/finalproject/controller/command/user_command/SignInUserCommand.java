package com.epam.finalproject.controller.command.user_command;

import com.epam.finalproject.constants.MappingConstants;
import com.epam.finalproject.constants.RequestParamsConstants;
import com.epam.finalproject.controller.command.CommandStrategy;
import com.epam.finalproject.model.dao.implementation.DAOFactory;
import com.epam.finalproject.model.dao.implementation.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignInUserCommand extends CommandStrategy {
    private final String invalidDataMessage = "Invalid user name or password...";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        mappingConstants = MappingConstants.getInstance();
        requestParamsConstants = RequestParamsConstants.getInstance();
        dao = DAOFactory.getDao(DAOFactory.DAOTypes.USER);
        String userName = request.getParameter(requestParamsConstants.getUserNameParam());
        String password = request.getParameter(requestParamsConstants.getPasswordParam());
        if (((UserDAOImpl) dao).isSignedUpUser(userName, password)) {
            HttpSession session = request.getSession();
            session.setAttribute(requestParamsConstants.getUserNameParam(), userName);
            response.sendRedirect(request.getContextPath() + mappingConstants.getPathToListCommand());
        } else {
            request.setAttribute("SERVLET_MESSAGE", invalidDataMessage);
            request.getRequestDispatcher(mappingConstants.getPathToSignInPage()).forward(request, response);
        }
    }
}
