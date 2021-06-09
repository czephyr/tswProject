package Servlet;

import Beans.AccountSession;
import DataAccess.OrderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ordersPageUser", value = "/ordersPageUser")
public class ordersPageUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		if (accountSession == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You're not logged");
			return;
		}

		OrderDao orderDao = new OrderDao();
		request.setAttribute("orders", orderDao.returnAllOrders(accountSession.getUserID()));
		request.getRequestDispatcher("orders.jsp").forward(request, response);
	}
}
