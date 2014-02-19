<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Servlet</title>
<link rel="stylesheet" href="style.css" type='text/css'>
</head>
<body>

<form action="SimpleServlet" method="post">
<table>
<tr>
<td>Name</td>
<td><input type="text" name="name"></td>
</tr>
<tr>
<td>Message</td>
<td><input type="text" name="message"></td>
</tr>
</table>
<br>
<input type="submit" value="submit">
</form>
</body>
</html>