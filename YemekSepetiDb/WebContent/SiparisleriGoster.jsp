<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.ServletException"%>

<%
	Runtime runtime = Runtime.getRuntime();
	String file = "C:\\Users\\canmurat\\Desktop\\eclipse-jee-juno-SR1-win32-x86_64\\eclipse\\siparisler.txt";
	Process p = runtime.exec("notepad " + file);
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
</head>
<body>

	<div id='kullanicilarigoster'>
		<h6>Siparis Sayfasina don</h6>
		<form action='SiparisServlet' method='get'>
			<input type='submit' class='btn btn-warning' name='stilladmin'
				value='Siparis sayfasina don'>

		</form>
	</div>

</body>
</html>