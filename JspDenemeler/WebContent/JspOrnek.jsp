<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>JSP Expressions</TITLE>
<META NAME="keywords" CONTENT="JSP,expressions,JavaServer Pages">
<META NAME="description" CONTENT="A quick example of JSP expressions.">
<LINK REL=STYLESHEET HREF="JSP-Styles.css" TYPE="text/css">
</HEAD>

<BODY>
	<H2>JSP Expressions</H2>
	<UL>
		<LI>Current Time: <%=new java.util.Date()%>
		<LI>Server: <%=application.getServerInfo()%>
		<LI>Session ID: <%=session.getId()%>
		<LI>The <CODE>testParam</CODE> form parameter: <%=request.getParameter("testParam")%>
	</UL>
</BODY>
</HTML>