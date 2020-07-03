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
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserCommand extends CommandStrategy {
    private final String passwordsNotMatchingMessage = "Entered new passwords are not matching!";
    private final String oldAndNewPasswordSameMessage = "Old and new passwords are the same!";
    private final String invalidPasswordMessage = "New password is invalid!";


    @Override
    protected void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        inputUtil = new InputUtil();
        dao = DAOFactory.getDao(DAOFactory.DAOTypes.USER);
        requestParamsConstants = RequestParamsConstants.getInstance();
        mappingConstants = MappingConstants.getInstance();
        GetUserCommand getUserCommand = new GetUserCommand();
        String userName = request.getParameter(requestParamsConstants.getUserNameParam());
        String oldPassword = request.getParameter(requestParamsConstants.getOldPasswordParam());
        String newPassword = request.getParameter(requestParamsConstants.getNewPasswordParam());
        String confirmedNewPassword = request.getParameter(requestParamsConstants.getConfirmedNewPasswordParam());
        if (!oldPassword.equals(newPassword)) {
            if (inputUtil.isValidPassword(newPassword)) {
                if (newPassword.equals(confirmedNewPassword)) {
                    int id = Integer.parseInt(request.getParameter(requestParamsConstants.getUserIdParam()));
                    ((UserDAOImpl) dao).updateUser(new User
                            .Builder()
                            .id(id)
                            .userName(userName)
                            .password(newPassword)
                            .build());
                } else {
                    request.setAttribute("SERVLET_MESSAGE", passwordsNotMatchingMessage);
                    getUserCommand.execute(request, response);
                }
            } else {
                request.setAttribute("SERVLET_MESSAGE", invalidPasswordMessage);
                getUserCommand.execute(request, response);
            }
        } else {
            request.setAttribute("SERVLET_MESSAGE", oldAndNewPasswordSameMessage);
            getUserCommand.execute(request, response);
        }
    }


}
