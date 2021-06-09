package Servlet;

import Beans.AccountSession;
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
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		if (accountSession == null || !accountSession.isUserIsAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You're not authorized");
			return;
		}

		UserDao userDao = new UserDao();
		int userID = Integer.parseInt(request.getParameter("userid"));
		try {
			if (userDao.deleteUserByID(userID)) {
				response.sendRedirect(request.getContextPath() + "/adminDashboard");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
