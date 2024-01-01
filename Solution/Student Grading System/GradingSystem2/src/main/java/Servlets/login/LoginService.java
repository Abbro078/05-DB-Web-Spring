package Servlets.login;

import Servlets.Models.Student;

import java.sql.SQLException;

public class LoginService {
	private LoginDao loginDao;

	public LoginService() throws SQLException {
		loginDao = new LoginDao();
	}

	public boolean validateUser(String user, String password) throws SQLException {
		return loginDao.isExist(user, password);
	}

	public Student getStudent(String email) throws SQLException {
		return loginDao.getStudent(email);
	}
}