package Servlets.login;

import Servlets.Models.Course;
import Servlets.Models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    private final Connection connection;

    public LoginDao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/atypon";
        String uName = "root";
        String uPassword = "admin";
        connection = DriverManager.getConnection(url, uName, uPassword);
        Statement statement = connection.createStatement();
    }

    public boolean isExist(String email, String password) throws SQLException {
        String query = "SELECT * FROM Student WHERE Student_Email=? AND Student_Password=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public Student getStudent(String email) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String courseQuery = "SELECT course.Course_ID, course.Course_Name, studentcourserelation.Mark " +
                "FROM studentcourserelation " +
                "INNER JOIN student ON student.Student_ID = studentcourserelation.Student_ID " +
                "INNER JOIN course ON course.Course_ID = studentcourserelation.Course_ID " +
                "WHERE student.Student_Email=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(courseQuery)) {
            preparedStatement.setString(1, email);
            try (ResultSet courseSet = preparedStatement.executeQuery()) {
                while (courseSet.next()) {
                    courses.add(new Course(courseSet.getInt(1), courseSet.getString(2), courseSet.getInt(3)));
                }
            }
        }

        String studentQuery = "SELECT Student_ID, Student_Email, Student_Name, Major FROM student WHERE student.Student_Email=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(studentQuery)) {
            preparedStatement.setString(1, email);
            try (ResultSet studentSet = preparedStatement.executeQuery()) {
                if (studentSet.next()) {
                    return new Student(studentSet.getInt(1), studentSet.getString(2),
                            studentSet.getString(3), studentSet.getString(4), courses);
                }
            }
        }
        return null;
    }
}
