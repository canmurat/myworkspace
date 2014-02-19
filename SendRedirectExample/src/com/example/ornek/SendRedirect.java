package com.example.ornek;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendRedirect extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SendRedirect() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
			response.sendRedirect("http://localhost:8080/SendRedirectExample/servlet/com.example.ornek.ValidUser");
	
	}
}