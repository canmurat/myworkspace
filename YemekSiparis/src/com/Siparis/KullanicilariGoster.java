package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/KullanicilariGoster")
public class KullanicilariGoster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public KullanicilariGoster() {
		super();

	}

	/*Admin kullanicilari goster butonuna týkladýgý zaman calýsacak servlet..'
	 * kullanicilar.txt dosyasý acýlýyor..*/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Runtime runtime = Runtime.getRuntime();
		String file = "C:\\Users\\canmurat\\Desktop\\eclipse-jee-juno-SR1-win32-x86_64\\eclipse\\kullanicilar.txt";
		Process p = runtime.exec("notepad " + file);

		
		/*Siparis Servlet'e geri don.*/
		out.println("<html>"+ 
					"<head>"+ 
				    "<link  href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
					"<link  href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
					
				   "</head>"+
				   "<body>" +
				"<div id='kullanicilarigoster'>"+
				"<h6> Siparis Sayfasina don </h6>"+
				"<form action='SiparisServlet' method='get'>"+
				"<input type='submit' class='btn btn-warning' name='stilladmin' value='Siparis sayfasina don'>"+
				
				   "</form>"+
				   "</div>"+
				   "</html>"+
				   "</body>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
