package Servlet;

import Beans.AccountSession;
import Beans.Cart;
import DataAccess.CartDao;
import DataAccess.OrderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "buyCartUser", value = "/buyCartUser")
public class buyCartUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDao orderDao = new OrderDao();
		CartDao cartDao = new CartDao();
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		try {
			int cartId= cartDao.checkCartExistance(accountSession.getUserID());
			Cart cart = cartDao.returnFullCart(cartId);
			orderDao.buyFullCart(cartId, accountSession.getUserID(),cart.getCartProducts(),cart.getCartTotal());
			cartDao.emptyCurrentCart(accountSession.getUserID());

			cart.setCartProducts(new ArrayList<>());

			session.setAttribute("cart",cart);
			session.setAttribute("cartItemsN",0);

			response.sendRedirect(request.getContextPath()+"/cartUser");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
}
