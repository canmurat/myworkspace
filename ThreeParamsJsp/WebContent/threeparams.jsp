<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Reading Three Request Parameters</TITLE>
<LINK REL=STYLESHEET HREF="JSP-Styles.css" TYPE="text/css">
</HEAD>
<BODY>
	<H1>Reading Three Request Parameters</H1>
	<UL>
		<LI><B>1.parametrem : </B>: <%=request.getParameter("param1")%>
		<LI><B>2.parametrem : </B>: <%=request.getParameter("param2")%>
		<LI><B>3.parametrem : </B>: <%=request.getParameter("param3")%>
	</UL>
</BODY>
</HTML>