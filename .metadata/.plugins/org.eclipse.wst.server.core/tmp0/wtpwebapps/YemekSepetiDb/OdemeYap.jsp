<%@include file="include/common.jsp"%>

<%
	// getParameter ile alacagımız degeri int'e atamadıgımız icin String degiskene atıp int olana cast ediyoruz 
	KrediKartNo1 = request.getParameter("KrediKartNo");
	if (KrediKartNo1 != null || !"".equals(KrediKartNo1))
		KrediKartNo = Long.parseLong(KrediKartNo1);
	// Bilgiler setAttribute ile alınıyor ve Odeme islemleri icin veritabanına gonderiliyor.
	KullaniciId = (Integer)session.getAttribute("KullaniciId");
	Yemekler =(String)session.getAttribute("alinanyemekler");
	Icecekler =(String)session.getAttribute("alinanicecekler");
	Salatalar =(String)session.getAttribute("alinansalatalar");
	Tatlilar =(String)session.getAttribute("alinantatlilar");
	
	yemekFiyat =(Integer)session.getAttribute("yemeklertoplam");
	icecekFiyat =(Integer)session.getAttribute("iceceklertoplam");
	salataFiyat =(Integer)session.getAttribute("salatalartoplam");
	tatliFiyat =(Integer)session.getAttribute("tatlilartoplam");
	
	//Yemek degerleri null ise bos string ile degistirlmesini sagladım.
	
	Yemekler = (Yemekler == null) ? "" : Yemekler;
	Icecekler =(Icecekler == null) ? "" : Icecekler;
	Salatalar = (Salatalar == null) ? "" : Salatalar;
	Tatlilar = (Tatlilar == null) ? "" : Tatlilar;
	
	Yemekler += " "+yemekFiyat +" TL";
	Icecekler+= " "+icecekFiyat + "TL";
	Salatalar+= " "+salataFiyat + "TL";
	Tatlilar += " "+tatliFiyat + "TL";
	
	// guncel saat bilgisini ilgili siniflardan elde edip veritabnına yazdiriyorum.
	
	java.util.Date today = new java.util.Date();
	java.sql.Date sqlToday = new java.sql.Date(today.getTime());
 	timeStamp = new java.text.SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(java.util.Calendar.getInstance().getTime());
	pstmt = con
			.prepareStatement("Insert into siparisler (KullaniciId,Yemekler,Icecekler,Salatalar,Tatlilar,Tarih)values(?,?,?,?,?,?)");
	pstmt.setInt(1, KullaniciId);
	pstmt.setString(2, Yemekler);
	pstmt.setString(3, Icecekler);
	pstmt.setString(4, Salatalar);
	pstmt.setString(5, Tatlilar);
	pstmt.setString(6, String.valueOf(timeStamp));
	
	
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("odeme", "odeme basari ile gerceklesmistir.");
	response.sendRedirect("yemek_sepetim.jsp");
%>