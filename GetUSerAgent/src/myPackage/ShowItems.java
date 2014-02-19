package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/ShowItems")
public class ShowItems extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList previousItems = (ArrayList) session
				.getAttribute("previousItems");
		if (previousItems == null) {
			previousItems = new ArrayList();
			session.setAttribute("previousItems", previousItems);
		}
		String newItem = request.getParameter("newItem");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Items Purchased";
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 "
				+ "Transitional//EN\">\n";
		out.println(docType + "<HTML>\n" + "<HEAD><TITLE>" + title
				+ "</TITLE></HEAD>\n" + "<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1>"
				+ title + "</H1>");
		synchronized (previousItems) {
//			if (newItem != null) {
				previousItems.add("elma");
//			}
			if (previousItems.size() == 0) {
				out.println("<I>No items</I>");

			} else {
				out.println("<UL>");
				for (int i = 0; i < previousItems.size(); i++) {
					out.println("<LI>" + (String) previousItems.get(i));
				}
				out.println("</UL>");
			}
		}
		out.println("</BODY></HTML>");
	}
}