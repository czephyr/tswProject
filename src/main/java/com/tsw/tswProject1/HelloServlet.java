package com.tsw.tswProject1;

import DataAccess.UserDao;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
	private String message;

	public void init() {
		message = "Hello World!";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		UserDao dao = new UserDao();

		response.setContentType("text/html");

		// Hello
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		try {
			out.println("<h1>" + dao.getAllUsers() + "</h1>");
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		out.println("</body></html>");
	}

	public void destroy() {
	}
}