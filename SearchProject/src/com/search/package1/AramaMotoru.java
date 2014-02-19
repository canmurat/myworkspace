package com.search.package1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AramaMotoru")

public class AramaMotoru extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AramaMotoru() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		out.println( 
		"<HTML>\n" +
		"<HEAD><TITLE> Arama Motoru </TITLE> </HEAD>\n"+
		"<BODY> \n" +
		"<FORM action = 'MySearch' method = 'Post'>\n" +
		"Arama Kelimesi Giriniz : <input type='text' name='AranacakKelime'> </br>"+
		"<p> Arama motorunu secin</p></br>"+ 
		"<input type = 'radio' name = 'AramaMotoru' value= 'Yandex'>Yandex  </br>"+
		"<input type = 'radio' name = 'AramaMotoru' value= 'Yahoo'>Yahoo </br>"+
		"<input type = 'radio' name = 'AramaMotoru' value= 'Google'>Google  </br>"+ 
		"<input type = 'radio' name = 'AramaMotoru' value= 'Ask'>Ask  </br>" +
		"<input type = 'radio' name = 'AramaMotoru' value= 'Bing'>Bing </br>"+
		"<input type = 'submit' name = 'submit' value = 'ARA' >"+
		"</FORM></br>"+
		"</BODY>"+
		"</HTML>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
	}

}
