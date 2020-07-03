package com.epam.finalproject.controller.command.user_command;

import com.epam.finalproject.constants.MappingConstants;
import com.epam.finalproject.constants.RequestParamsConstants;
import com.epam.finalproject.controller.command.CommandStrategy;
import com.epam.finalproject.model.dao.implementation.DAOFactory;
import com.epam.finalproject.model.dao.implementation.UserDAOImpl;
import com.epam.finalproject.model.entity.User;
import com.epam.finalproject.util.InputUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpUserCommand extends CommandStrategy {
    private final String userExistsMessage = "User with such username already exists!";
    private final String passwordsNotMatch = "Entered passwords are not matching!";
    private final String invalidLoginMessage = "User name should be 3-45 characters long and contain only a-z, A-Z, 0-9 and _";
    private final String invalidPasswordMessage = "Password should be 8-16 characters long and contain only a-z, A-Z, 0-9 or _!@#%";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        inputUtil = new InputUtil();
        dao = DAOFactory.getDao(DAOFactory.DAOTypes.USER);
        requestParamsConstants = RequestParamsConstants.getInstance();
        mappingConstants = MappingConstants.getInstance();
        String userName = request.getParameter(requestParamsConstants.getUserNameParam());
        String password = request.getParameter(requestParamsConstants.getPasswordParam());
        String confirmedPassword = request.getParameter(requestParamsConstants.getConfirmedPasswordParam());
        if (inputUtil.isValidUserName(userName)) {
            if (inputUtil.isValidPassword(password)) {
                if (password.equals(confirmedPassword)) {
                    User userToSignUp = new User
                            .Builder()
                            .userName(userName)
                            .password(password)
                            .build();
                    if (((UserDAOImpl) dao).signUpUser(userToSignUp)) {
                        HttpSession session = request.getSession();
                        session.setAttribute(requestParamsConstants.getUserNameParam(), userName);
                        response.sendRedirect(request.getContextPath() + mappingConstants.getPathToListCommand());
                    } else {
                        request.setAttribute("SERVLET_MESSAGE", userExistsMessage);
                        request.getRequestDispatcher(mappingConstants.getPathToSignUpPage()).forward(request, response);
                    }
                } else {
                    request.setAttribute("SERVLET_MESSAGE", passwordsNotMatch);
                    request.getRequestDispatcher(mappingConstants.getPathToSignUpPage()).forward(request, response);
                }
            } else {
                request.setAttribute("SERVLET_MESSAGE", invalidPasswordMessage);
                request.getRequestDispatcher(mappingConstants.getPathToSignUpPage()).forward(request, response);
            }
        } else {
            request.setAttribute("SERVLET_MESSAGE", invalidLoginMessage);
            request.getRequestDispatcher(mappingConstants.getPathToSignUpPage()).forward(request, response);
        }
    }
}
