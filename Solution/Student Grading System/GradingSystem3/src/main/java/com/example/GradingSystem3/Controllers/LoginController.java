package com.example.GradingSystem3.Controllers;

import com.example.GradingSystem3.Models.Student;
import com.example.GradingSystem3.Services.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public void showLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @PostMapping
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        try {
            boolean isValidUser = loginService.validateUser(name, password);
            if (isValidUser) {
                Student student = loginService.getStudent(name);
                request.setAttribute("student", student);
                request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Invalid Credentials!!");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while processing the login request.", e);
        }
    }
}
