<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<HTML>
<HEAD>
<TITLE>Random Numbers</TITLE>
<LINK REL=STYLESHEET HREF="JSP-Styles.css" TYPE="text/css">
</HEAD>
<BODY>
	<H1>Random Numbers</H1>
	<UL>
		<LI><%=coreservlets.RanUtilities.randomInt(10)%>
		<LI><%=coreservlets.RanUtilities.randomInt(10)%>
		<LI><%=coreservlets.RanUtilities.randomInt(10)%>
		<LI><%=coreservlets.RanUtilities.randomInt(10)%>
		<LI><%=coreservlets.RanUtilities.randomInt(10)%>
	</UL>
</BODY>
</HTML>