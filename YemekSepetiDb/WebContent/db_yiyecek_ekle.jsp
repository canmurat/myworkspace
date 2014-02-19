<%@include file="include/common.jsp"%>

<%
	// getParameter ile alacagımız degeri int'e atamadıgımız icin String degiskene atıp int olana cast ediyoruz 
	yemekFiyat1 = request.getParameter("yiyecekFiyati");
	if (yemekFiyat1 != null || !"".equals(yemekFiyat1))
		yemekFiyat = Integer.parseInt(yemekFiyat1);

	yemekAd = request.getParameter("yiyecekAdi");

	pstmt = con
			.prepareStatement("Insert into yiyecekler(Ad,Fiyat) values(?,?)");
	pstmt.setString(1, yemekAd);
	pstmt.setInt(2, yemekFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("yiyecek", "yemek  (" + yemekAd
			+ ") basari ile eklenmistir.");
	response.sendRedirect("yiyecek_ekle.jsp");
%>