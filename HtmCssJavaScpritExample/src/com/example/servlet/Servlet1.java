package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Servlet1() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println("<html>" +
				"<head>" +
				"<title>JavaScript Example</title>" +
				"<link  href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
				"</head>" +
					"<body>" +
					"<div id=\"div1\" >" +
						"<p> div1 burada yatmaktadýr. </p>" +
						"<div id=\"div2\" >" +
							"<p> div2 burada yatmaktadýr. </p>" +
						"</div>"+
						"<div id=\"div3\" >" +
							"<p> div3 burada yatýyor. </p> " +
						"</div>"+
					"<div id=\"div4\" >"+
					"<p> div4 </p>"+
					"</div>" +
					"</div>"+
					
					
					
					"</body>" + 
					"</html>");
					
				

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
