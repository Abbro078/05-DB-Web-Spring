package com.example.GradingSystem3.Daos;

import java.sql.*;

public class CourseDao {
    private final Statement statement;

    public CourseDao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/atypon";
        String uName = "root";
        String uPassword = "admin";
        Connection connection = DriverManager.getConnection(url, uName, uPassword);
        statement = connection.createStatement();
    }

    public ResultSet getData(int courseId) throws SQLException {
        String marksQuery = "SELECT studentcourserelation.Mark FROM studentcourserelation WHERE Course_ID=" + courseId + ";";
        return statement.executeQuery(marksQuery);
    }

    public ResultSet getCourseData(int courseId) throws SQLException {
        String query = "SELECT * FROM course WHERE Course_ID=\"" + courseId + "\";";
        return statement.executeQuery(query);
    }
}
