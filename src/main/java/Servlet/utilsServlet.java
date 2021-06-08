package Servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class utilsServlet extends HttpServlet {

	protected String getPath(HttpServletRequest request){
		return  (request.getPathInfo() != null) ? request.getPathInfo() : "/";
	}
}
