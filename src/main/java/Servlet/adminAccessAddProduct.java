package Servlet;

import Beans.AccountSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "adminAccessAddProduct", value = "/adminAccessAddProduct")
public class adminAccessAddProduct extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		if (accountSession == null || !accountSession.isUserIsAdmin()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You're not authorized");
			return;
		}

		request.getRequestDispatcher("WEB-INF/addproduct.jsp").forward(request, response);
	}
}
