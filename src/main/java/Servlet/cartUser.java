package Servlet;

import Beans.AccountSession;
import Beans.Cart;
import DataAccess.CartDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "cartUser", value = "/cartUser")
public class cartUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartDao cartDao = new CartDao();
		AccountSession account = (AccountSession) request.getSession(false).getAttribute("accountSession");
		Cart cart = null;
		try {
			cart = cartDao.returnFullCart(cartDao.checkCartExistance(account.getUserID()));
			request.setAttribute("cart", cart);
			request.setAttribute("total", cart.getCartTotal());
			request.getRequestDispatcher("cart.jsp").forward(request,response);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
