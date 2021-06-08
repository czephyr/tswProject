package Servlet;

import DataAccess.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "adminDeleteUser", value = "/adminDeleteUser")
public class adminDeleteUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		int userID = Integer.parseInt(request.getParameter("userid"));
		try {
			if(userDao.deleteUserByID(userID)){
				response.sendRedirect(request.getContextPath()+"/adminDashboard");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
