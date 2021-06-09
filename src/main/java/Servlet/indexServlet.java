package Servlet;

import Beans.Product;
import DataAccess.ProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "indexServlet", value = "/index")
public class indexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDao productDao = new ProductDao();
		ArrayList<Product> homeProducts = new ArrayList<>();

		try {
			homeProducts = productDao.find3mostQuantity();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		request.setAttribute("homeProducts", homeProducts);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
