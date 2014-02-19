package com.Siparis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GirisServlet
 */
@WebServlet("/GirisServlet")
public class GirisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UyeClass uye = new UyeClass();
	
    public GirisServlet() {
        super();
       
     
    }
	
    /*Giris Servlet'inde ya giris yapilir , ya da uye olunur.
     * Giris yapildigi taktirde UyeSorgulaServlet'e gidilir,
     * Uye ol denildigiinde uye servlet'e gidilir.
     * Hatalý islemler sonucunda Hatalar ile birlikte Giris Servlet'ine geri donulur, 
     * Hata yok ise SiparisServlet'e giris saglanýr .. */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		boolean newbie = true;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ((c.getName().equals("repeatVisitor"))
						&& (c.getValue().equals("yes"))) {
					newbie = false;
					break;
				}

			}
		}
		String title;
		if (newbie) {
			Cookie returnVisitorCookie = new Cookie("repeatVisitor", "yes");
			returnVisitorCookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(returnVisitorCookie);
			title = "Welcome Aboard";
		} else {
			title = "Welcome Back";
		}
		
	        
		
		out.println(
				"<!DOCTYPE html>"+
				"<html>"+
				"<head>"+
				"<title>"+ title + "</title>"+
				"<link  href=\"bootstrap/css/bootstrap.css\" rel=\"stylesheet\" type=\"text/css\" />\n"+
				"<link  href=\"css/styles.css\" rel=\"stylesheet\" type=\"text/css\" />"+
				"</head>"+
				"<body>"+
				"<div id='buyukdiv' >"+
				
					"<div id='girissol'>"+
					"<form action='UyeSorgulaServlet'id='ilkLogin' method='get'>"+
						"<h1> GIRIS YAP </h1>"+
						"<input type='text' class=\"form-control\" name='username' placeholder='Kullanici Adi'><br>"+
						"<input type='password'  class=\"form-control\" name='password' placeholder='Sifre'><br>"+
						"<input type='submit' class='btn btn-danger' value='Giris Yap'>"+
		 			"</form>"+
						
					"</div>"+
					
					"<div id='ortadakitanitim'>"+
					"</div>"+
			
					"<div id='uyeolsag'>"+
					
						"<form action='Uyeler' id='UyeOl' method='get'>"+
						"<h1> UYE OL </h1>"+
						"<input type='text' class=\"form-control\" name='username' placeholder='Kullanici adi'><br>"+
						"<input type='text' class=\"form-control\" name='usernametekrar' placeholder='Kullanici adi Tekrar'><br>"+
						"<input type='password'  class=\"form-control\" name='password' placeholder='Sifre'><br>"+
						"<input type='password'  class=\"form-control\" name='passwordtekrar' placeholder='Sifre'><br>"+
						"<input type='text' name='mail' placeholder='MAIL' ><br>"+
						"<input type='submit' class='btn btn-danger' value='UYE OL'>"+
					
						"</form>"+
					"</div>"+
					"<div id='asagidakidiv'>"+
				
				
					"</div>"+
				"</div>"+
				"</body>"+

				"</html>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
