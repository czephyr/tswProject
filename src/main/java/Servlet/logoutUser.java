package Servlet;

import Beans.AccountSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "logoutUser", value = "/logoutUser")
public class logoutUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
		if (accountSession == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You're not logged");
			return;
		}

		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index");
	}
}
