<%@include file="include/common.jsp"%>

<%
	// getParameter ile alacagımız degeri int'e atamadıgımız icin String degiskene atıp int olana cast ediyoruz 

	Ad = request.getParameter("Ad");
	Soyad = request.getParameter("Soyad");
	DTarih = request.getParameter("DTarih");
	Adres = request.getParameter("Adres");
	EPosta = request.getParameter("EPosta");
	Tel1 = request.getParameter("Tel");
	Sifre1 = request.getParameter("Sifre");
	Admin1 = request.getParameter("Admin");
	KrediKartNo1= request.getParameter("KrediKartNo");

	if (Tel1 != null || !"".equals(Tel1))
		Tel = Long.parseLong(Tel1);
	
	if (KrediKartNo1 != null || !"".equals(KrediKartNo1))
		KrediKartNo = Long.parseLong(KrediKartNo1);
	
	if (Sifre1 != null || !"".equals(Sifre1))
		Sifre = Long.parseLong(Sifre1);
	
	if (Admin1 != null || !"".equals(Admin1))
		Admin = Integer.parseInt(Admin1);
	

	pstmt = con
			.prepareStatement("Insert into kullanicilar(Ad,Soyad,DTarih,Adres,EPosta,Tel,Sifre,Admin,KrediKartNo) values(?,?,?,?,?,?,?,?,?)");
	pstmt.setString(1, Ad);
	pstmt.setString(2, Soyad);
	pstmt.setString(3, DTarih);
	pstmt.setString(4, Adres);
	pstmt.setString(5, EPosta);
	pstmt.setLong(6, Tel);
	pstmt.setLong(7, Sifre);
	pstmt.setInt(8, Admin);
	pstmt.setLong(9, KrediKartNo);

	pstmt.executeUpdate();

	con.close();
	session.setAttribute("kullanici", "kullanici  (" + Ad
			+ ") basari ile eklenmistir.");
	response.sendRedirect("kullanici_ekle.jsp");
%>