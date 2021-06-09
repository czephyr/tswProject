package Servlet;

import DataAccess.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "catalogueServlet", value = "/catalogueServlet")
public class catalogueServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao productDao = new ProductDao();

		if ((request.getParameter("search") != null) && (request.getParameter("orderBy") != null)) {
			try {
				request.setAttribute("products", productDao.getAllSearch(request.getParameter("search"), request.getParameter("orderBy")));
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		} else {
			try {
				request.setAttribute("products", productDao.getAllProducts());
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		request.getRequestDispatcher("WEB-INF/catalogue.jsp").forward(request, response);
	}
}
