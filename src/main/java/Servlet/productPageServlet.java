package Servlet;

import DataAccess.ProductDao;
import DataAccess.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "productPageServlet", value = "/productPageServlet")
public class productPageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao productDao = new ProductDao();
		try {
			request.setAttribute("product", productDao.findByID(Integer.parseInt(request.getParameter("ID"))));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		request.getRequestDispatcher("product.jsp").forward(request,response);
	}

}
