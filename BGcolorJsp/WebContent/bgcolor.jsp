<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Color Testing</TITLE>
</HEAD>
<%
	String bgColor = request.getParameter("bgColor");
	if ((bgColor == null) || (bgColor.trim().equals(""))) {
		bgColor = "WHITE";
	}
%>
<BODY BGCOLOR="<%=bgColor%>">
	<H2 ALIGN="CENTER">
		Testing a Background of "<%=bgColor%>".
	</H2>
</BODY>
</HTML>