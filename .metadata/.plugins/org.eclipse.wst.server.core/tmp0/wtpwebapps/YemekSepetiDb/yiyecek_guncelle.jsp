<%@include file="include/common.jsp"%>

<%

// degerler getParameter ile alındı. Sonradan int olanlar int'degiskene aktarıldı.
	yemekId1 = request.getParameter("yemekId");
	if (yemekId1 != null)
		yemekId = Integer.parseInt(yemekId1);

	yemekAd = request.getParameter("yemekAd");

	yemekFiyat1 = request.getParameter("yemekFiyat");
	if (yemekFiyat1 != null || !"".equals(yemekFiyat1))
		yemekFiyat = Integer.parseInt(yemekFiyat1);
// guncelleme degiskenleri common.jsp'de tanimli
	pstmt = con
			.prepareStatement("Update yiyecekler set Ad=?,Fiyat=? where Id="
					+ yemekId);

	pstmt.setString(1, yemekAd);
	pstmt.setInt(2, yemekFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("guncel_yemek", " ("
			+ yemekAd + ") yemegi basari ile guncellendi !");
%>
<!-- sayfanın çıktı değerinin değil, içeriğinin yorumlanmadan 
jsp ye direkt dahil edilmesi için:
 -->
<jsp:forward page="yiyecek_duzenle.jsp">
	<jsp:param name="yemekId" value="<%=yemekId%>" />
</jsp:forward>
