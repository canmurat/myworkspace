<%@include file="include/common.jsp"%>

<%
	// getParameter ile alacagımız degeri int'e atamadıgımız icin String degiskene atıp int olana cast ediyoruz 
	icecekFiyat1 = request.getParameter("icecekFiyati");
	if (icecekFiyat1 != null || !"".equals(icecekFiyat1))
		icecekFiyat = Integer.parseInt(icecekFiyat1);

	icecekAd = request.getParameter("icecekAdi");

	pstmt = con
			.prepareStatement("Insert into icecekler(Ad,Fiyat) values(?,?)");
	pstmt.setString(1, icecekAd);
	pstmt.setInt(2, icecekFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("icecek", "icecek  (" + icecekAd
			+ ") basari ile eklenmistir.");
	response.sendRedirect("icecek_ekle.jsp");
%>