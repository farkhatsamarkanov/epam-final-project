package com.epam.finalproject.model.dao.implementation;

import java.io.IOException;
import java.sql.SQLException;

public class DAOFactory {
    public enum DAOTypes {
        STUDENT,
        USER
    }

    public static AbstractDAO getDao(DAOTypes type) throws SQLException, IOException, ClassNotFoundException {
        AbstractDAO daoToReturn = null;
        switch (type) {
            case USER:
                daoToReturn = new UserDAOImpl();
                break;
            case STUDENT:
                daoToReturn = new StudentDAOImpl();
                break;
            default:
                throw new IllegalArgumentException("Wrong DAO type " + type);
        }
        return daoToReturn;
    }

}
