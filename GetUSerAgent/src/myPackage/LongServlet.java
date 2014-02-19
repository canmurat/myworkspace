package myPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/long-servlet")
public class LongServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// Change the definition of "out" depending on
		// whether or not gzip is supported.
		PrintWriter out;
		if (GzipUtilities.isGzipSupported(request)
				&& !GzipUtilities.isGzipDisabled(request)) {
			out = GzipUtilities.getGzipWriter(response);
			response.setHeader("Content-Encoding", "gzip");
		} else {
			String docType = "doctoyyye";
			String title = "title";
			out = response.getWriter();
			out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title
					+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n"
					+ "<H1 ALIGN=\"CENTER\">" + title + "</H1>\n");
			String line = "Blah, blah, blah, blah, blah. "
					+ "Yadda, yadda, yadda, yadda.";
			for (int i = 0; i < 10000; i++) {
				out.println(line);
			}
			out.println("</BODY></HTML>");
			out.close();
		}
	}

}