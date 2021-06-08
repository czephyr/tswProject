package Servlet;

import DataAccess.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "adminAddProduct", value = "/adminAddProduct")
public class adminAddProduct extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao productDao = new ProductDao();

		try {
			productDao.createProduct(request.getParameter("name"), Double.parseDouble(request.getParameter("price")), request.getParameter("description"), Integer.parseInt(request.getParameter("quantity")), request.getParameter("category"));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath()+"/adminDashboard");
	}
}
