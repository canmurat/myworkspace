<%@include file="include/common.jsp"%>

<%
	tatliId1 = request.getParameter("tatliId");
	if (tatliId1 != null)
		tatliId = Integer.parseInt(tatliId1);

	tatliAd = request.getParameter("tatliAd");

	tatliFiyat1 = request.getParameter("tatliFiyat");
	if (tatliFiyat1 != null || !"".equals(tatliFiyat1))
		tatliFiyat = Integer.parseInt(tatliFiyat1);

	pstmt = con
			.prepareStatement("Update tatlilar set Ad=?,Fiyat=? where Id="
					+ tatliId);

	pstmt.setString(1, tatliAd);
	pstmt.setInt(2, tatliFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("guncel_tatli", " ("
			+ tatliAd + ") tatli basari ile guncellendi !");
%>
<!-- sayfanın çıktı değerinin değil, içeriğinin yorumlanmadan 
jsp ye direkt dahil edilmesi için:
 -->
<jsp:forward page="tatli_duzenle.jsp">
	<jsp:param name="tatliId" value="<%=tatliId%>" />
</jsp:forward>
