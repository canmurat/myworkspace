<%@include file="include/common.jsp"%>

<%
	// getParameter ile alacagımız degeri int'e atamadıgımız icin String degiskene atıp int olana cast ediyoruz 
	salataFiyat1 = request.getParameter("salataFiyati");
	if (salataFiyat1 != null || !"".equals(salataFiyat1))
		salataFiyat = Integer.parseInt(salataFiyat1);

	salataAd = request.getParameter("salataAdi");

	pstmt = con
			.prepareStatement("Insert into salatalar(Ad,Fiyat) values(?,?)");
	pstmt.setString(1, salataAd);
	pstmt.setInt(2, salataFiyat);
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("salata", "salata  (" + salataAd
			+ ") basari ile eklenmistir.");
	response.sendRedirect("salata_ekle.jsp");
%>