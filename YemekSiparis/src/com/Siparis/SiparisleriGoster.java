package com.Siparis;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@WebServlet("/SiparisleriGoster")
public class SiparisleriGoster extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SiparisleriGoster() {
		super();

	}

	
	/*Siparisleri gostermek icin dosyayi calýstýracak (acacak ) kod. Not: Dosya sadece admin ise acilabilir.*/
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Runtime runtime = Runtime.getRuntime();
		String file ="C:\\Users\\canmurat\\Desktop\\eclipse-jee-juno-SR1-win32-x86_64\\eclipse\\siparisler.txt";
		Process p = runtime.exec("notepad " +file);
		
		/*Dosyayi goruntuledikten sonra Siparis Servlet'e geri donebilmesi icin.*/
		out.println("<html>"+ 
				"<head>"+ 
			    "<link  href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
				"<link  href=\"bootstrap/css/bootstrap.min.css\" rl=\"stylesheet\" type=\"text/css\" />\n"+
				
			   "</head>"+
			   "<body>" +
			"<div id='kullanicilarigoster'>"+
			"<h6> Siparis Sayfasina don </h6>"+
			"<form action='SiparisServlet' method='get'>"+
			"<input type='submit' class='btn btn-warning' name='stilladmin' value='Siparis sayfasina don'>"+
			
			   "</form>"+
			   "</div>"+
			  
			   "</body>"+
			   "</html>");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
