package myPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		boolean isMissingValue = false;
		String firstName = request.getParameter("firstName");
		if ((firstName) == null) {
			firstName = "Missing first name";
			isMissingValue = true;
		}
		String lastName = request.getParameter("lastName");
		if ((lastName)== null) {
			lastName = "Missing last name";
			isMissingValue = true;
		}

		Cookie c1 = new LongLivedCookie("firstName", firstName);
		response.addCookie(c1);
		Cookie c2 = new LongLivedCookie("lastName", lastName);
		response.addCookie(c2);
	
		if (isMissingValue) {
			response.sendRedirect("registration-form");
		} else {
		}

	}
}