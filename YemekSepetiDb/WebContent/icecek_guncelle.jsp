<%@include file="include/common.jsp"%>

<%
	icecekId1 = request.getParameter("icecekId");
	if (icecekId1 != null)
		icecekId = Integer.parseInt(icecekId1);

	icecekAd = request.getParameter("icecekAd");

	icecekFiyat1 = request.getParameter("icecekFiyat");
	if (icecekFiyat1 != null || !"".equals(icecekFiyat1))
		icecekFiyat = Integer.parseInt(icecekFiyat1);

	pstmt = con
			.prepareStatement("Update icecekler set Ad=?,Fiyat=? where Id="
					+ icecekId);

	pstmt.setString(1, icecekAd);
	pstmt.setInt(2, icecekFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("guncel_icecek", " ("
			+ icecekAd + ") icecek basari ile guncellendi !");
%>
<!-- sayfanın çıktı değerinin değil, içeriğinin yorumlanmadan 
jsp ye direkt dahil edilmesi için:
 -->
<jsp:forward page="icecek_duzenle.jsp">
	<jsp:param name="icecekId" value="<%=icecekId%>" />
</jsp:forward>
