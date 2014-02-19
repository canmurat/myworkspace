
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<HTML>
<HEAD>
<TITLE>Semi-Random Number</TITLE>
<LINK REL=STYLESHEET HREF="JSP-Styles.css" TYPE="text/css">
</HEAD>
<BODY>
	<%!private int randomNum = coreservlets.RanUtilities.randomInt(10);%>
	<H1>
		Semi-Random Number:<BR><%=randomNum%></H1>
</BODY>
</HTML>