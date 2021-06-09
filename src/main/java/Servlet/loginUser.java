package Servlet;

import Beans.AccountSession;

import Beans.Cart;
import Beans.Product;
import DataAccess.CartDao;
import DataAccess.UserDao;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


@WebServlet(name = "loginUser", value = "/loginUser")
public class loginUser extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		AccountSession accountSession = null;
		Cart cart = new Cart();
		int cartItemsN = 0;
		try {
			accountSession = userDao.findUserForLogin(request.getParameter("email"), request.getParameter("password"));
			if (accountSession == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Wrong email or password");
				return;
			}

			int cartid = cartDao.checkCartExistance(accountSession.getUserID());
			if (cartid > 0) {
				cart = cartDao.returnQuantityIDInCart(accountSession.getUserID(), cartid);
				for (Product product : cart.getCartProducts()) {
					cartItemsN = cartItemsN + product.getProductQuantity();
				}
			} else {
				cartDao.createCartByID(accountSession.getUserID());
				cartid = cartDao.checkCartExistance(accountSession.getUserID());
			}

			cart.setCartID(cartid);

			HttpSession session = request.getSession(true);
			session.setAttribute("accountSession", accountSession);
			session.setAttribute("cart", cart);
			session.setAttribute("cartItemsN", cartItemsN);

		} catch (SQLException | NoSuchAlgorithmException throwables) {
			throwables.printStackTrace();
		}
	}
}
