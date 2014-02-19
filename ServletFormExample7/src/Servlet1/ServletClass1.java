package Servlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletClass1 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html"); PrintWriter out = response.getWriter(); out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n" + "<HTML>\n" + "<HEAD><TITLE>Reading All Request Parameters</TITLE></HEAD>\n" + "</HTML>"+

"<BODY BGCOLOR=\"#FDF5E6\">\n" + "<H1 ALIGN=CENTER>" + "</H1>\n" + "<TABLE BORDER=1 ALIGN=CENTER>\n" + "<TR BGCOLOR=\"#FFAD00\">\n" + "<TH>Parameter Name<TH>Parameter Value(s)"); Enumeration paramNames = request.getParameterNames(); while(paramNames.hasMoreElements()) { String paramName = (String)paramNames.nextElement(); out.println("<TR><TD>" + paramName + "\n<TD>"); String[] paramValues = request.getParameterValues(paramName); if (paramValues.length == 1) { String paramValue = paramValues[0]; if (paramValue.length() == 0) out.print("<I>No Value</I>"); else out.print(paramValue); } else { out.println("<UL>"); for(int i=0; i<paramValues.length; i++) { out.println("<LI>" + paramValues[i]); } out.println("</UL>"); } } out.println("</TABLE>\n</BODY></HTML>"); }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}