package com.epam.finalproject.controller.command;

import com.epam.finalproject.constants.MappingConstants;
import com.epam.finalproject.constants.RequestParamsConstants;
import com.epam.finalproject.model.dao.implementation.AbstractDAO;
import com.epam.finalproject.util.InputUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class CommandStrategy {
    protected InputUtil inputUtil;
    protected AbstractDAO dao;
    protected MappingConstants mappingConstants;
    protected RequestParamsConstants requestParamsConstants;

    protected abstract void execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException;
}
