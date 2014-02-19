<%@include file="include/common.jsp"%>

<%
	// getParameter ile alacagımız degeri int'e atamadıgımız icin String degiskene atıp int olana cast ediyoruz 
	tatliFiyat1 = request.getParameter("tatliFiyati");
	if (tatliFiyat1 != null || !"".equals(tatliFiyat1))
		tatliFiyat = Integer.parseInt(tatliFiyat1);

	tatliAd = request.getParameter("tatliAdi");

	pstmt = con
			.prepareStatement("Insert into tatlilar(Ad,Fiyat) values(?,?)");
	pstmt.setString(1, tatliAd);
	pstmt.setInt(2, tatliFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("tatli", "tatli  (" + tatliAd
			+ ") basarıi ile eklenmistir.");
	response.sendRedirect("tatli_ekle.jsp");
%>