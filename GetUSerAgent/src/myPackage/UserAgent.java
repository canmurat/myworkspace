package myPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAgent
 */
@WebServlet("/UserAgent")
public class UserAgent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAgent() {
		super();
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userAgent = request.getHeader("User-Agent");
		String title = "User Agent ";
		String browser = null;
		if ((userAgent != null)) {
			
			 if(userAgent.contains("Chrome")){ //checking if Chrome)
				 {
					 browser = "Crome";
				 }
			 }
			 else if(userAgent.contains("Firefox")) //checking if Firefox
			 {
				 browser = "Firefox";
				 
			 }
			 else if(userAgent.contains("MSIE"))  // Checking if Microsoft
			 {
				 browser = "Windows";
			 }

			}
			else 
			{
				browser = "Unknown browser ";
			}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>\n" + "<HEAD><TITLE>" + "</TITLE></HEAD>\n"
				+ "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=\"CENTER\">"
				+"Welcome "+browser+ " User" + "</H1>\n" + "<B>Request Method: </B>"
				+ request.getMethod() + "<BR>\n" + "<B>Request URI: </B>"
				+ request.getRequestURI() + "<BR>\n"
				+ "<B>Request Protocol: </B>" + request.getProtocol()
				+ "<BR><BR><BR>\n"  + " and Here is your browser's details.. \n"
				+ request.getHeader("user-agent") + "</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
