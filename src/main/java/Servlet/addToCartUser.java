package Servlet;

import Beans.AccountSession;
import Beans.Cart;
import Beans.Product;
import DataAccess.CartDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "addToCartUser", value = "/addToCartUser")
public class addToCartUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		if(accountSession == null){
			response.sendError(HttpServletResponse.SC_FORBIDDEN,"You're not logged");
			return;
		}

		CartDao cartDao = new CartDao();
		Cart cart = (Cart) session.getAttribute("cart");
		ArrayList<Product> cartProducts = cart.getCartProducts();

		Product product = new Product(Integer.parseInt(request.getParameter("productid")), 1);

		cartProducts.add(product);
		cart.setCartProducts(cartProducts);

		session.setAttribute("cart", cart);
		session.setAttribute("cartItemsN",((Integer)session.getAttribute("cartItemsN"))+1);

		if(cartDao.itemIsInCart(Integer.parseInt(request.getParameter("productid")),cart.getCartID())){
			cartDao.updateQuantity(Integer.parseInt(request.getParameter("productid")),cart.getCartID());
		}else{
			cartDao.addToCartByID(Integer.parseInt(request.getParameter("productid")),cart.getCartID());
		}

		response.sendRedirect(request.getContextPath()+"/productPageServlet" + "?ID="+ request.getParameter("productid"));
	}
}
