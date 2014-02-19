<%@page import="com.sepetim.UyeYaz"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>

<%
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
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>insert title here</title>

<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div id='buyukdiv'>

		<div id='girissol'>

		<!--  Giris Yaparken Uye Sorgula servlet'e gidiyor ve uye sorguluyot
		uye var ise AnaSayfa'ya yonlendiriliyor.Yoksa Giris sayfasına yonlendiriliyor.
		 -->
			<form action='UyeSorgula.jsp' id='ilkLogin' method='get'>
				<h1>GIRIS YAP</h1>
				<input type='text' class="form-control" name='username'><br>
				<input type='password' class="form-control" name='password'><br>


				<input type='submit' class='btn btn-danger' value='Giris Yap'>
			</form>
	   <!-- Uye OL butonuna basildiginda UyeOlSayfa ya yonlendiriliyor. Uye ollmak icin bir sorun yoksa 
	    Uye olunuyor. varsa UyeOlSayfaya tekrar yonlendirilip hatalar donduruluyor. -->
			<form action='UyeOlSayfa.jsp' id='UyeOl' method='get'>

				<input type='submit' class='btn btn-warning' value='UYE OL'>

			</form>
			<!-- 							<input type='submit' class='btn btn-warning' value='Uye Ol' > -->
			<%
		// UyeSorgulama sonucunda uye bulunamaz ise setAttribute ile gelen mesaj gosteriliyor. 
		// SOnra attribute remove ediliyor.
				if(session.getAttribute("uyeBilgi") != null)
					out.println((String)session.getAttribute("uyeBilgi"));
					session.removeAttribute("uyeBilgi");
					System.out.println("uye bilgi= "+session.getAttribute("uyeBilgi"));
			%>


		</div>

		<div id='ortadakitanitim'></div>
	<!--  Kullanici Cikis yapacagı zaman eger admin ise admin olma ozelligini kaybetsin diye kontrol -->
	<% if(session.getAttribute("stilladmin")!=null)
		{
			session.removeAttribute("stilladmin");
		}%>
	</div>


</body>
</html>