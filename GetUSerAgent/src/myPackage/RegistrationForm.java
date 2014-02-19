package myPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registration-form")
public class RegistrationForm extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String firstName = CookieUtilities.getCookieValue(request, "firstName",
				"");
		String lastName = CookieUtilities.getCookieValue(request, "lastName",
				"");
		String emailAddress = CookieUtilities.getCookieValue(request,
				"emailAddress", "");

		out.println("" + "<HTML>\n" + "<HEAD><TITLE>" + ""
				+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
				+ "<CENTER>\n" + "<H1>" + "" + "</H1>\n"
				+ "<FORM ACTION=\"registration\">\n" + "First Name:\n"
				+ " <INPUT TYPE=\"TEXT\" NAME=\"firstName\" " + "VALUE=\""
				+ firstName + "\"><BR>\n" + "Last Name:\n"
				+ " <INPUT TYPE=\"TEXT\" NAME=\"lastName\" " + "VALUE=\""
				+ lastName + "\"><BR>\n" + "Email Address: \n"
				+ " <INPUT TYPE=\"TEXT\" NAME=\"emailAddress\" " + "VALUE=\""
				+ emailAddress + "\"><P>\n"
				+ "<INPUT TYPE=\"SUBMIT\" VALUE=\"Register\">\n"
				+ "</FORM></CENTER></BODY></HTML>");
	}

}
