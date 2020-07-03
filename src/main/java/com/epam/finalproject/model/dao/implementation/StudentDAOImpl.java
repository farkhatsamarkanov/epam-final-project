package com.epam.finalproject.model.dao.implementation;

import com.epam.finalproject.model.dao.StudentDAO;
import com.epam.finalproject.model.entity.Student;
import com.epam.finalproject.model.jdbc.DBConnectionPool;
import com.epam.finalproject.constants.DBConstants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl extends AbstractDAO implements StudentDAO {

    private DBConstants dbConstants;
    private DBConnectionPool dbConnectionPool;
    private final String SELECT_ALL_STUDENTS = "select * from student order by id";
    private final String INSERT_STUDENT = "insert into student (first_name, last_name, email) values (?, ?, ?)";
    private final String SELECT_SPECIFIC_STUDENT = "select * from student where id=?";
    private final String UPDATE_STUDENT = "update student set first_name=?, last_name=?, email=? where id=?";
    private final String DELETE_STUDENT = "delete from student where id=?";
    private final String SEARCH_STUDENTS = "select * from student where lower(first_name) like ? or lower(last_name) like ?";

    StudentDAOImpl() throws IOException, SQLException, ClassNotFoundException {
        dbConstants = DBConstants.getInstance();
        dbConnectionPool = DBConnectionPool.getInstance(dbConstants.getDriver(), dbConstants.getUrl(), dbConstants.getUser(), dbConstants.getPassword(), dbConstants.getPoolSize());
    }

    public DBConnectionPool getDbConnectionPool() {
        return dbConnectionPool;
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            String sql = SELECT_ALL_STUDENTS;
            statement = connection != null ? connection.prepareStatement(sql) : null;
            resultSet = statement != null ? statement.executeQuery(sql) : null;
            while (resultSet != null && resultSet.next()) {
                int studentId = resultSet.getInt(dbConstants.getId());
                String firstName = resultSet.getString(dbConstants.getFirstName());
                String lastName = resultSet.getString(dbConstants.getLastName());
                String email = resultSet.getString(dbConstants.getEmail());
                Student tempStudent = new Student
                        .Builder()
                        .id(studentId)
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .build();
                students.add(tempStudent);
            }
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, resultSet);
        }
        return students;
    }

    @Override
    public boolean addStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(INSERT_STUDENT);
                statement.setString(1, student.getFirstName());
                statement.setString(2, student.getLastName());
                statement.setString(3, student.getEmail());
                return statement.executeUpdate() > 0;
            }
            LOG.info("No connection available to add student");
            return false;
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, null);
        }
    }

    @Override
    public Student getStudent(String studentId) throws SQLException {
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int studentIdInt;
        try {
            studentIdInt = Integer.parseInt(studentId);
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(SELECT_SPECIFIC_STUDENT);
                statement.setInt(1, studentIdInt);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String firstName = resultSet.getString(dbConstants.getFirstName());
                    String lastName = resultSet.getString(dbConstants.getLastName());
                    String email = resultSet.getString(dbConstants.getEmail());
                    student = new Student
                            .Builder()
                            .id(studentIdInt)
                            .firstName(firstName)
                            .lastName(lastName)
                            .email(email)
                            .build();
                } else {
                    LOG.error("Could not find student id: " + studentId);
                    throw new SQLException("Could not find student id: " + studentId);
                }
            }
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, resultSet);
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(UPDATE_STUDENT);
                statement.setString(1, student.getFirstName());
                statement.setString(2, student.getLastName());
                statement.setString(3, student.getEmail());
                statement.setInt(4, student.getId());
                return statement.executeUpdate() > 0;
            }
            return false;
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, null);
        }
    }

    @Override
    public boolean deleteStudent(String studentId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            int studentIdInt = Integer.parseInt(studentId);
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                statement = connection.prepareStatement(DELETE_STUDENT);
                statement.setInt(1, studentIdInt);
                return statement.executeUpdate() > 0;
            }
            return false;
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, null);
        }
    }

    @Override
    public List<Student> searchStudents(String searchBoxCriterion) throws SQLException {
        List<Student> studentsFound = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            if (connection != null) {
                if (searchBoxCriterion != null && searchBoxCriterion.trim().length() > 0) {
                    statement = connection.prepareStatement(SEARCH_STUDENTS);
                    String searchBoxContentLike = "%" + searchBoxCriterion.toLowerCase() + "%";
                    statement.setString(1, searchBoxContentLike);
                    statement.setString(2, searchBoxContentLike);
                } else {
                    statement = connection.prepareStatement(SELECT_ALL_STUDENTS);
                }
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int studentId = resultSet.getInt(dbConstants.getId());
                    String firstName = resultSet.getString(dbConstants.getFirstName());
                    String lastName = resultSet.getString(dbConstants.getLastName());
                    String email = resultSet.getString(dbConstants.getEmail());
                    studentsFound.add(new Student
                            .Builder()
                            .id(studentId)
                            .firstName(firstName)
                            .lastName(lastName)
                            .email(email)
                            .build());
                }
            }
        } finally {
            dbConnectionPool.putConnection(connection);
            close(statement, resultSet);
        }
        return studentsFound;
    }
}
