package Servlet;

import DataAccess.ProductDao;
import DataAccess.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "adminDashboard", value = "/adminDashboard")
public class adminDashboard extends utilsServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
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
		request.getRequestDispatcher("WEB-INF/adminpage.jsp").forward(request,response);
	}

}
