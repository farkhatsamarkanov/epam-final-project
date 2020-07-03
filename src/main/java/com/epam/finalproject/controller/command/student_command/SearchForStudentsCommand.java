package com.epam.finalproject.controller.command.student_command;

import com.epam.finalproject.constants.MappingConstants;
import com.epam.finalproject.constants.RequestParamsConstants;
import com.epam.finalproject.controller.command.CommandStrategy;
import com.epam.finalproject.model.dao.implementation.DAOFactory;
import com.epam.finalproject.model.dao.implementation.StudentDAOImpl;
import com.epam.finalproject.model.entity.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchForStudentsCommand extends CommandStrategy {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        dao = DAOFactory.getDao(DAOFactory.DAOTypes.STUDENT);
        requestParamsConstants = RequestParamsConstants.getInstance();
        mappingConstants = MappingConstants.getInstance();
        String searchBoxContent = request.getParameter(requestParamsConstants.getSearchBoxContentParam());
        List<Student> students = ((StudentDAOImpl) dao).searchStudents(searchBoxContent);
        request.setAttribute(requestParamsConstants.getStudentListParam(), students);
        RequestDispatcher dispatcher = request.getRequestDispatcher(mappingConstants.getPathToListPage());
        dispatcher.forward(request, response);
    }
}
