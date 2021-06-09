package Servlet;

import Beans.AccountSession;
import DataAccess.ProductDao;
import DataAccess.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "adminDashboard", value = "/adminDashboard")
public class adminDashboard extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		if (accountSession == null || !accountSession.isUserIsAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You're not authorized");
			return;
		}


		ProductDao productDao = new ProductDao();
		UserDao userDao = new UserDao();

		try {
			request.setAttribute("products", productDao.getAllProducts());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		try {
			request.setAttribute("users", userDao.getAllUsers());
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request, response);
	}

}
