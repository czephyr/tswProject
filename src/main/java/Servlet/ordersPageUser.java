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
		OrderDao orderDao = new OrderDao();
		AccountSession account = (AccountSession) request.getSession(false).getAttribute("accountSession");
		request.setAttribute("orders",orderDao.returnAllOrders(account.getUserID()));
		request.getRequestDispatcher("orders.jsp").forward(request,response);
	}
}
