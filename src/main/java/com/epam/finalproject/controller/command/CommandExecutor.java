package com.epam.finalproject.controller.command;

import com.epam.finalproject.controller.command.app_command.ChangeLocaleCommand;
import com.epam.finalproject.controller.command.student_command.*;
import com.epam.finalproject.controller.command.user_command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CommandExecutor {
    private CommandStrategy command = null;

    public CommandExecutor() {
    }

	public void executeCommand(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String commandParameter = request.getParameter("command");
    	if (commandParameter == null) {
			commandParameter = "LIST";
		}
    	switch(commandParameter) {
			case "LIST":
				command = new GetAllStudentsCommand();
				break;
			case "LOAD-STUDENT":
				command = new GetStudentCommand();
				break;
			case "UPDATE-STUDENT":
				command = new UpdateStudentCommand();
				command.execute(request, response);
				command = new GetAllStudentsCommand();
				break;
			case "DELETE-STUDENT":
				command = new DeleteStudentCommand();
				command.execute(request, response);
				command = new GetAllStudentsCommand();
				break;
			case "DELETE-USER":
				command = new DeleteUserCommand();
				command.execute(request, response);
				command = new SignOutUserCommand();
				break;
			case "SEARCH":
				command = new SearchForStudentsCommand();
				break;
			case "SIGNOUT":
				command = new SignOutUserCommand();
				break;
			case "ADD":
				command = new AddStudentCommand();
				response.sendRedirect(request.getContextPath() + "/ControllerServlet?command=LIST");
				break;
			case "SIGNIN":
				command = new SignInUserCommand();
				break;
			case "SIGNUP":
				command = new SignUpUserCommand();
				break;
			case "UPDATE-USER":
				command = new UpdateUserCommand();
				command.execute(request, response);
				command = new GetAllStudentsCommand();
				break;
			case "LOAD-USER":
				command = new GetUserCommand();
				break;
			case "CHANGE-LOCALE":
				command = new ChangeLocaleCommand();
				break;
			default:
				command = new GetAllStudentsCommand();;
		}
		command.execute(request, response);
    }


}
