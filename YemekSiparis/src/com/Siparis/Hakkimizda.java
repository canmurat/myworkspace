package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hakkimizda")
public class Hakkimizda extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Hakkimizda() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println("<html>"
				+ "<body>"
				+ "<div id='hakkimizdaustdiv'>"
				+ "</div>"
				+ "<div id='hakkimizdadiv'>"
				+ "<h3> WWW.YEMEKYE.COM , GELISEN VE YENILENEN ARAYUZU ILE SIZLERLE BULUSUYOR .. </h3>"
				+ "<h3> RESTAURANT'IMIZA DA BEKLERÝZ..</h3>"
				+ "<div id='hakkimizdaaltdiv'>"
				+ "</div>"
				+ "</div>"
				+ "<head>"
				+ "<link  href=\"css/styles.css\" rel=\"stylesheet\" type=\"text/css\" />\n"

				+ "</head>" + "</body>" + "</html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
