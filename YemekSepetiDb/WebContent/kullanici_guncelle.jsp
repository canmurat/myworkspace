<%@include file="include/common.jsp"%>

<%
	KullaniciId1 = request.getParameter("KullaniciId");
	if (KullaniciId1 != null)
		KullaniciId = Integer.parseInt(KullaniciId1);

	Ad = request.getParameter("Ad");
	Soyad =request.getParameter("Soyad");
	DTarih =request.getParameter("DTarih");
	Adres =request.getParameter("Adres");
	EPosta =request.getParameter("EPosta");
	
	Tel1 = request.getParameter("Tel");
	if (Tel1 != null || !"".equals(Tel1))
		Tel = Long.parseLong(Tel1);
	
	Sifre1 = request.getParameter("Sifre");
	if (Sifre1 != null || !"".equals(Sifre1))
		Sifre = Long.parseLong(Sifre1);
	
	Admin1 = request.getParameter("Admin");
	if (Admin1 != null || !"".equals(Admin1))
		Admin = Integer.parseInt(Admin1);
	
	KrediKartNo1 = request.getParameter("KrediKartNo");
	if (KrediKartNo1 != null || !"".equals(KrediKartNo1))
		KrediKartNo = Long.parseLong(KrediKartNo1);
	


	pstmt = con
			.prepareStatement("Update kullanicilar set Ad=?,Soyad=?,DTarih=?,Adres=?,EPosta=?,Tel=?,Sifre=?,Admin=?,KrediKartNo=? where Id="
					+ KullaniciId);

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
	session.setAttribute("guncel_kullanici", " ("
			+ Ad + ") kullanici basari ile guncellendi !");
%>
<!-- sayfanın çıktı değerinin değil, içeriğinin yorumlanmadan 
jsp ye direkt dahil edilmesi için:
 -->
<jsp:forward page="kullanici_duzenle.jsp">
	<jsp:param name="KullaniciId" value="<%=KullaniciId%>" />
</jsp:forward>
