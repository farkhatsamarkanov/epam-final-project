package com.epam.finalproject.model.dao;

import com.epam.finalproject.model.entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    List getAllStudents() throws SQLException;

    boolean addStudent(Student student) throws SQLException;

    Student getStudent(String studentId) throws SQLException;

    boolean updateStudent(Student student) throws SQLException;

    boolean deleteStudent(String studentId) throws SQLException;

    List<Student> searchStudents(String searchBoxCriterion) throws SQLException;
}
