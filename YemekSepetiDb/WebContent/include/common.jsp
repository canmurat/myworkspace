<%@include file="./database.jsp"%>

<%
	String yemekAd = "", yemekFiyat1 = "", urun1 = "", yemekId1 = "", guncel_yemek = "";
	String icecekAd = "", icecekFiyat1 = "", urun2 = "", icecekId1 = "", guncel_icecek = "";
	String salataAd = "", salataFiyat1 = "", urun3 = "", salataId1 = "", guncel_salata = "";
	String tatliAd = "", tatliFiyat1 = "", urun4 = "", tatliId1 = "", guncel_tatli = "";
	String yemegi_sil = "", icecegi_sil = "", salatayi_sil = "", tatliyi_sil = "", kullanici_sil = "", urunu_sil = "";
	String Ad = "", Soyad = "", DTarih = "", Adres = "", EPosta = "", guncel_kullanici = "";
	String KullaniciId1 = "", Tel1 = "", Sifre1 = "", Para1 = "", KrediKartNo1 = "", Admin1 = "", kullanici = "";

	String sepet_yemek_sil ="", sepet_icecek_sil="", sepet_salata_sil="", sepet_tatli_sil=""; 
	String timeStamp = "";
	int yemekId = 0, yemekFiyat = 0, icecekId = 0, icecekFiyat = 0, salataId = 0, salataFiyat = 0, tatliId = 0, tatliFiyat = 0,geneltoplam=0;
	int KullaniciId = 0, Para = 0, Admin = 0, SiparisId1 = 0;
	long Tel = 0, KrediKartNo = 0, Sifre = 0;

	String alinanyemekler = "", alinanicecekler = "", alinansalatalar = "", alinantatlilar = "";
	String yemekleradet = "", icecekleradet = "",salatalaradet="", tatlilaradet = "";
	int yemeklertoplam = 0, iceceklertoplam = 0, salatalartoplam = 0, tatlilartoplam = 0, toplamtutar = 0;
	
	String yemeklertoplam1="",iceceklertoplam1="",salatalartoplam1="",tatlilartoplam1="";
	
	String SiparisleriAl = "SiparisleriAl";

	String SiparisId = "", Yemekler = "", Icecekler = "", Salatalar = "", Tatlilar = "";

	int x=0;

	
%>
