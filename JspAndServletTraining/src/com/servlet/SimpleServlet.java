package com.servlet;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class SimpleServlet extends HttpServlet {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	protected void processRequest(HttpServletRequest request, 
                                  HttpServletResponse response)
                                      throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String message = request.getParameter("message");

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>SimpleServlet</title>");  
            out.println("<link rel='stylesheet' href='style.css' type='text/css'>");
            out.println("</head>");
            out.println("<body>");
            if (name!=null && message!=null) {
                out.println(name + " Says:");
                out.println(message);
            }
            out.println("</body>");
            out.println("</html>");
            

        } finally { 
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
                             throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                              throws ServletException, IOException {
        processRequest(request, response);
    }
}