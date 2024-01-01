package com.example.GradingSystem3.Services;

import com.example.GradingSystem3.Daos.LoginDao;
import com.example.GradingSystem3.Models.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private LoginDao loginDao;

    {
        try {
            loginDao = new LoginDao();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateUser(String user, String password) throws SQLException {
        return loginDao.isExist(user, password);
    }

    public Student getStudent(String email) throws SQLException {
        return loginDao.getStudent(email);
    }
}

