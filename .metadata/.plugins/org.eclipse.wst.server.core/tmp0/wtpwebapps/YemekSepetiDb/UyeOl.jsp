<%@page import="com.sepetim.UyeYaz"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*;"%>


<%
	String sonNot = "";
	String[] illegalKarakter;
	boolean uyeOlabilir = true;
		String Tel1;
	String Sifre1;
	String SifreTekrar1;
	String KrediKartNo1;
	long Tel = 0;
	long Sifre = 0 ;
	long SifreTekrar = 0;
	long KrediKartNo = 0;
	
// 	Sifre1 =  request.getParameter("sifre");
// 	SifreTekrar1 = request.getParameter("sifreTekrar");
// 	KrediKartNo1 =  request.getParameter("KrediKartNo");

	String Ad = (request.getParameter("ad") == "") ? "" : (request.getParameter("ad"));
	String Soyad = (request.getParameter("soyad") == "") ? "" : (request.getParameter("soyad"));
	String DogumTarih = (request.getParameter("dogumTarih") == "") ? "" : (request.getParameter("dogumTarih"));
	String EPosta = (request.getParameter("ePosta") == "") ? "" : (request.getParameter("ePosta"));
	String Adres = (request.getParameter("adres") == "") ? "" : (request.getParameter("adres"));
	Tel = (request.getParameter("telefon") == "") ? 0: Long.parseLong(request.getParameter("telefon"));
	Sifre = (request.getParameter("sifre") == "") ? 0 : Long.parseLong(request.getParameter("sifre"));
	SifreTekrar = (request.getParameter("sifreTekrar") ==  "") ? 0 : Long.parseLong(request.getParameter("sifreTekrar"));
	KrediKartNo = (request.getParameter("KrediKartNo") == "") ? 0 : Long.parseLong(request.getParameter("KrediKartNo"));

	

	sonNot = "";
	
	if (Ad.isEmpty() || Soyad.isEmpty() || Tel ==0
			|| EPosta.isEmpty() || Adres.isEmpty() || Sifre==0
			|| SifreTekrar==0 || KrediKartNo ==0) {
		System.out.println("Hepsi bos birader ! ");
		sonNot = "<h3 id='h3'> HIC BIR ALAN BOS BIRAKILMAMALIDIR !</h3> \n";
		uyeOlabilir = false;

	}

	/* Girilen kullanici isminde bir kullanici mevcut ise uyarılır. */
	if (new com.sepetim.UyeAra(Ad).uyeVarmi()) {

		sonNot += "<h3 id='h3'>GIRILEN KULLANICI ADI KULLANILMAKTADIR ! !</h3>";
		uyeOlabilir = false;
	}
	/* Karakter sorgulaması */
	if (Ad.split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2).length > 1) {
		sonNot += "KULLANICI ISMI ICIN YANLIS KARAKTER GIRDINIZ ! \n"
				+ "[~#@*+%{}<>\\[\\]|\"\\_^] KARAKTERLERINDEN HERHANGI BIRISI KULLANILAMAZ !";
		uyeOlabilir = false;
	}
	/* Sifrelerin eslesip eslememe sorgusu */
	if (Sifre != SifreTekrar) {

		sonNot += "<h3 id='h3'> SIFRELER ESLESMIYOR !</h3>";
		uyeOlabilir = false;

	}

	// uye olamaz olarak belirlenmis ise (degiskenle) UyeOlSayfa.jsp'ye yondendirip hata mesajını gosteriyorum
	if (uyeOlabilir == false) {
		System.out.println("Son not= " + sonNot);
		session.setAttribute("sonNot", sonNot);
		response.sendRedirect("UyeOlSayfa.jsp");

	}
	System.out.println("uyeOlabilir durumu= " + uyeOlabilir);
	/* Sorun yok ise , girilen isimler kullanicilar.txt'e yazilir. */
	if (uyeOlabilir == true) {

	// uye olmada sorun varsa kullanıcya uye olmada sorun olustu mesajı
		try {
			new com.sepetim.UyeYaz(Ad, Soyad, DogumTarih, Tel,
					EPosta, Adres, Sifre, KrediKartNo);
		} catch (Exception ex) {
			System.out.println("UYE OLMADA SORUN");
		}

		sonNot += "<h3 id='h3'>Uye oldun hadi hayirli olsun !</h3>";
		
		// uye olmada sorun yok ise Isım bilgilerini Attribute ile gonderiyorum.
		session.setAttribute("durum", "UyeOl");
		session.setAttribute("AdSoyad", Ad + " " + Soyad);
		response.sendRedirect("SiparisSayfa.jsp");

	}
%>