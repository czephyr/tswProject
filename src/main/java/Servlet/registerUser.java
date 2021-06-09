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
			if(userDao.emailIsInDB(request.getParameter("email"))){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Email is already in the DB");
				return;
			}

			if(((String) request.getParameter("name")).length() <3 ){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Your name is too short");
				return;
			}

			if(((String) request.getParameter("surname")).length() <3 ){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Your surname is too short");
				return;
			}

			if(!((String) request.getParameter("email")).matches("^(([^<>()[\\]\\\\.,;:\\s@\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$") ){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Your email is not valid");
				return;
			}

			if(!((String) request.getParameter("password")).matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})") ){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Your password is not valid");
				return;
			}

			int userid = userDao.createUser(request.getParameter("email"),request.getParameter("name"),request.getParameter("surname"), request.getParameter("password"));
			int cartid = cartDao.createCartByID(userid);
			Cart cart = new Cart(userid,cartid);
			AccountSession accountSession = new AccountSession(userid,request.getParameter("email"),request.getParameter("name"),request.getParameter("surname"), false);

			HttpSession session = request.getSession(true);

			session.setAttribute("accountSession", accountSession);
			session.setAttribute("cart", cart);
			session.setAttribute("cartItemsN",0);
		} catch (SQLException | NoSuchAlgorithmException throwables) {
			throwables.printStackTrace();
		}
	}
}
