<%@include file="include/common.jsp"%>

<%
	salataId1 = request.getParameter("salataId");
	if (salataId1 != null)
		salataId = Integer.parseInt(salataId1);

	salataAd = request.getParameter("salataAd");

	salataFiyat1 = request.getParameter("salataFiyat");
	if (salataFiyat1 != null || !"".equals(salataFiyat1))
		salataFiyat = Integer.parseInt(salataFiyat1);

	pstmt = con
			.prepareStatement("Update salatalar set Ad=?,Fiyat=? where Id="
					+ salataId);

	pstmt.setString(1, salataAd);
	pstmt.setInt(2, salataFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("guncel_salata", " ("
			+ salataAd + ") salata basari ile guncellendi !");
%>
<!-- sayfanın çıktı değerinin değil, içeriğinin yorumlanmadan 
jsp ye direkt dahil edilmesi için:
 -->
<jsp:forward page="salata_duzenle.jsp">
	<jsp:param name="salataId" value="<%=salataId%>" />
</jsp:forward>
