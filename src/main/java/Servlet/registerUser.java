package Servlet;

import Beans.AccountSession;
import Beans.Cart;
import Beans.User;
import DataAccess.CartDao;
import DataAccess.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "registerUser", value = "/registerUser")
public class registerUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		try {
			int userid = userDao.createUser(request.getParameter("email"),request.getParameter("name"),request.getParameter("surname"), request.getParameter("password"));
			int cartid = cartDao.createCartByID(userid);

			Cart cart = new Cart(cartid);
			AccountSession accountSession = new AccountSession(request.getParameter("email"),request.getParameter("name"),request.getParameter("surname"), false);

			HttpSession session = request.getSession(true);

			session.setAttribute("accountSession", accountSession);
			session.setAttribute("cart", cart);
			session.setAttribute("cartItemsN",0);
		} catch (SQLException | NoSuchAlgorithmException throwables) {
			throwables.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/index");
	}
}
