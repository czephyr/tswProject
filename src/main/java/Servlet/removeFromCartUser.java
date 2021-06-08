package Servlet;

import Beans.AccountSession;
import Beans.Cart;
import Beans.Product;
import DataAccess.CartDao;
import DataAccess.ProductDao;
import org.javatuples.Pair;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "removeFromCartUser", value = "/removeFromCartUser")
public class removeFromCartUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		if(accountSession == null){
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"You're not logged");
			return;
		}

		CartDao cartDao = new CartDao();
		int cartItemsN = 0;
		Cart cart = (Cart) session.getAttribute("cart");
		ArrayList<Product> cartProducts = cart.getCartProducts();

		cartProducts.removeIf(product -> product.getProductID() == Integer.parseInt(request.getParameter("productid")));
		for (Product product: cartProducts) {
			cartItemsN = cartItemsN + product.getProductQuantity();
		}
		cart.setCartProducts(cartProducts);

		session.setAttribute("cart", cart);
		session.setAttribute("cartItemsN", cartItemsN);
		cartDao.removeFromCartByID(accountSession.getUserID(),Integer.parseInt(request.getParameter("productid")));
		response.sendRedirect(request.getContextPath()+"/cartUser");
	}
}
