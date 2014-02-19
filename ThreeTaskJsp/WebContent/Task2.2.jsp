
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<HTML>
<HEAD>
<TITLE>Random List (Version 2)</TITLE>
<LINK REL=STYLESHEET HREF="JSP-Styles.css" TYPE="text/css">
</HEAD>
<BODY>
	<H1>Random List (Version 2)</H1>
	<UL>
		<%
			int numEntries = coreservlets.RanUtilities.randomInt(10);
			for (int i = 0; i < numEntries; i++) {
		%>
		<LI><%=coreservlets.RanUtilities.randomInt(10)%> <%
 	}
 %>
	</UL>
</BODY>
</HTML>