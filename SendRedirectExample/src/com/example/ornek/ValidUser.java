package com.example.ornek;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ValidUser extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidUser() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw = response.getWriter();
		pw.println("Welcome to roseindia.net<br>");
		pw.println("how are you");
	}
}