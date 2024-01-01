package Servlets.login;

import Servlets.Models.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private LoginService service;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			service = new LoginService();
		} catch (SQLException e) {
			throw new ServletException("Error initializing LoginService", e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		try {
			if (service.validateUser(name, password)) {
				Student student = service.getStudent(name);
				request.setAttribute("student", student);
				request.getRequestDispatcher("/jsp/welcome.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Invalid Credentials!!");
				request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			throw new ServletException("Database error", e);
		}
	}
}
