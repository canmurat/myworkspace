<%@page import="com.sepetim.TabloVerileri"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="com.sepetim.TabloVerileri"%>


<%!public ArrayList<String> secilenYiyeceklerListesi = new ArrayList<String>();
	public ArrayList<String> secilenIceceklerListesi = new ArrayList<String>();
	public ArrayList<String> secilenTatlilarListesi = new ArrayList<String>();
	public int secilmisYemekMiktari;
	public int secilmisIcecekMiktari;
	public int secilmisTatliMiktari;

	public String yazilacak;
	public String durum;
	public String kullaniciadi;
	public String sifre;
	public static Boolean adminmi;%>

<%

	TabloVerileri tb = new TabloVerileri();
	/*Admin girisinden sonra normal kullanıcı girislerinde de 'admin-girisi' dememesi icin konuldu.*/
	adminmi = false;

	/*SiparisleriGoster ya da KullanicilariGoster servlet'lerinden geldigimizi anlamak icin bunu kullandım.
	 * Amac : bu servlet'lerden gelindigi taktirde hala admin olmayı saglamak..*/
	if (session.getAttribute("stilladmin") != null) {
		adminmi = true;
	}
	
	/*Siparis Servletine ya UyeSorgulaServlet'ten gelinebilir,Ya da UyeOlServletten.
	 * ikisinde de 'durum' isminde attiribute konulmustur.*/
	if (session.getAttribute("durum") != null) {

		durum = (String) session.getAttribute("durum");
		System.out.println("durum "+durum);
		/*Girilen durum attribute'si UyeSorgulaya ait ise kullanici ismini al.
		 */
		 
		 System.out.println("session.getAtrribute('user)'"+session.getAttribute("user"));
	
		if (durum == "UyeSorgula") {
			if (session.getAttribute("user").equals(true)) {
				adminmi = true;
				kullaniciadi = session.getAttribute("username")
						+ "-admin";
				

			}
			/*Admin ise Kulanicilari ve Siparisleri listeleyebilir.*/
			else {
				kullaniciadi = (String) session.getAttribute("username");

			}
		}
		session.setAttribute("user", false);
	}
	/*JavaScript kısmında gerekli hesap islemleri yapılmaktadır.
	 * Yardımcı sınıf olarak ServletUtilities sınıfı kullanıldı.(Amac tekrarları onlemek..)*/
	 
	
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script>
		function temizle() {
			document.getElementById('secilenyemeklertexti').value = '';
			document.getElementById('toplamyemeklertexti').value = '';
			document.getElementById('secileniceceklertexti').value = '';
			document.getElementById('toplamiceceklertexti').value = '';
			document.getElementById('secilentatlilartexti').value = '';
			document.getElementById('toplamtatlilartexti').value = '';
			document.getElementById('geneltoplamtexti').value = '';
			document.getElementById('secilensalatalartexti').value = '';
			document.getElementById('toplamsalatalartexti').value = '';

		}
		function yemekleriYazdirma() {

			var e = document.getElementById('yiyeceklerkutusu');
			var secilenYemekFiyati = parseInt(e.options[e.selectedIndex].innerHTML
					.replace(/[^0-9]/g, ''));

			var secilenYemek = e.options[e.selectedIndex].value.replace(
					/[0-9]/g, '');

			var adet = parseInt(document.getElementById('yemekadetkutusu').value);
			if (adet == '' || isNaN(adet)) {
				adet = 1;
			}
			document.getElementById('secilenyemeklertexti').value += secilenYemek
					+ '(' + adet + ' adet),';
			document.getElementById('toplamyemeklertexti').value = +document
					.getElementById('toplamyemeklertexti').value
					+ (secilenYemekFiyati * adet);
			var genelToplam = parseInt(document
					.getElementById('geneltoplamtexti').value);
			if (!isNaN(genelToplam)) {

				document.getElementById('geneltoplamtexti').value = genelToplam
						+ secilenYemekFiyati * adet;
			} else {
				document.getElementById('geneltoplamtexti').value = secilenYemekFiyati
						* adet;
			}

			document.getElementById('yemekadetkutusu').value = 1;
		}

		function icecekleriYazdirma() {
			var e = document.getElementById('iceceklerkutusu');
			secilenIcecek = e.options[e.selectedIndex].value.replace(/[0-9]/g,
					'');
			var secilenIcecekFiyati = parseInt(e.options[e.selectedIndex].innerHTML
					.replace(/[^0-9]/g, ''));
			var adet = parseInt(document.getElementById('icecekadetkutusu').value);
			if (adet == '' || isNaN(adet)) {
				adet = 1;
			}
			document.getElementById('secileniceceklertexti').value += secilenIcecek
					+ '(' + adet + ' adet),';
			document.getElementById('toplamiceceklertexti').value = +document
					.getElementById('toplamiceceklertexti').value
					+ secilenIcecekFiyati * adet;
			var genelToplam = parseInt(document
					.getElementById('geneltoplamtexti').value);
			if (!isNaN(genelToplam)) {

				document.getElementById('geneltoplamtexti').value = genelToplam
						+ secilenIcecekFiyati * adet;
			} else {
				document.getElementById('geneltoplamtexti').value = secilenIcecekFiyati
						* adet;
			}
			document.getElementById('icecekadetkutusu').value = 1;
		}

		function salatalariYazdirma() {
			var e = document.getElementById('salatalarkutusu');
			secilenYemek = e.options[e.selectedIndex].value.replace(/[0-9]/g,
					'');
			var secilenYemekFiyati = parseInt(e.options[e.selectedIndex].innerHTML
					.replace(/[^0-9]/g, ''));
			var adet = parseInt(document.getElementById('salataadetkutusu').value);
			if (adet == '' || isNaN(adet)) {
				adet = 1;
			}
			document.getElementById('secilensalatalartexti').value += secilenYemek
					+ '(' + adet + ' adet),';

			document.getElementById('toplamsalatalartexti').value = +document
					.getElementById('toplamsalatalartexti').value
					+ (secilenYemekFiyati * adet);
			var genelToplam = parseInt(document
					.getElementById('geneltoplamtexti').value);
			if (!isNaN(genelToplam)) {

				document.getElementById('geneltoplamtexti').value = genelToplam
						+ secilenYemekFiyati * adet;
			} else {
				document.getElementById('geneltoplamtexti').value = secilenYemekFiyati
						* adet;
			}

			document.getElementById('salataladetkutusu').value = 1;
		}

		function tatlilariYazdirma() {
			var e = document.getElementById('tatlilarkutusu');
			secilenTatli = e.options[e.selectedIndex].value.replace(/[0-9]/g,
					'');
			var secilenTatliFiyati = parseInt(e.options[e.selectedIndex].innerHTML
					.replace(/[^0-9]/g, ''));
			var adet = parseInt(document.getElementById('tatliadetkutusu').value);
			if (adet == '' || isNaN(adet)) {
				adet = 1;
			}
			document.getElementById('secilentatlilartexti').value += secilenTatli
					+ '(' + adet + ' adet),';
			document.getElementById('toplamtatlilartexti').value = +document
					.getElementById('toplamtatlilartexti').value
					+ secilenTatliFiyati * adet;
			var genelToplam = parseInt(document
					.getElementById('geneltoplamtexti').value);
			if (!isNaN(genelToplam)) {

				document.getElementById('geneltoplamtexti').value = genelToplam
						+ secilenTatliFiyati * adet;
			} else {
				document.getElementById('geneltoplamtexti').value = secilenTatliFiyati
						* adet;
			}
			document.getElementById('tatliadetkutusu').value = 1;
		}
	</script>

	<div id="anadiv">
		<div id="headerdiv"></div>
		<div id="ustdiv">
			<ul>
				<li><a href="SiparisSayfa.jsp">Ana Sayfa</a></li>
				<li><a href="http://localhost:8080/YemekSiparis/Hakkimizda">
						Hakkimizda </a></li>
				<li><a href="http://localhost:8080/YemekSiparis/Iletisim">
						Iletisim </a></li>
				<li><a
					href="http://localhost:8080/YemekSepetiDb/GirisSayfa.jsp">


						Cikis Yap </a></li>

			</ul>
		</div>

		<%
			session.setAttribute("kullaniciAdi", kullaniciadi);
		%>
		<div id="solparca">

			<%
				/*İF ELSE bloklarında :
				 * -Kullanicinin admin olmasına gore ,
				 * -SiparisServlet'e gelinen yere gore
				 *   Belirlemeler yapılmakta ve Sayfanın sol kısmın ona gore dizayn edilmektedir.*/

				if (durum == "UyeSorgula" && !adminmi) {

					System.out.println(adminmi + "adminmi");
					
			%>
			
			
			<h4>HOSGELDINIZ!!</h4>
			<h4><%=kullaniciadi%>
			</h4>
			<h4>SIPARISINIZI YAPINIZ</h4>
			
			<div id="neyiyelim">
			
			</div>
			<%
				} else if (durum == "UyeOl") {
				%>	
					<h3>HOSGELDINIZ <%=session.getAttribute("AdSoyad")%> </h3>
					<div id="neyiyelim">
			
					</div>
					
			<%session.removeAttribute("AdSoyad");

				}

				else if (durum == "SiparisleriAl") {
			%>

			<h4>Secilen Yemekler Yazdirilmistir !</h4>
			<div id="neyiyelim">
			
			</div>
			<%
				}

				else if (adminmi) {
			%>
			<h5>
				<%=kullaniciadi%>
			</h5>
			<div id="neyiyelim">
			
			</div>

			<h5>Admin Girisi</h5>
			<form action='siparisleri_goruntule.jsp' class='btn btn-success'
				method='get'>
				<br> <input type='submit' value='SIPARISLERI GOSTER'>
			</form>
			
			<form action='yiyecek_goruntule.jsp' class='btn btn-success'
				method='post'>
				<br> <input type='submit' value='YEMEK ISLEMLERI'>
			</form>
			<br> <br>

			<form action='icecek_goruntule.jsp' class='btn btn-success'
				method='post'>
				<br> <input type='submit' value='ICECEK ISLEMLERI'>
			</form>
			

			<form action='salata_goruntule.jsp' class='btn btn-success'
				method='post'>
				<br> <input type='submit' value='SALATA ISLEMLERI'>
			</form><br> <br>

			<form action='tatli_goruntule.jsp' class='btn btn-success'
				method='post'>
				<br> <input type='submit' value='TATLI ISLEMLERI'>
			</form>
			

			<form action='kullanici_goruntule.jsp' class='btn btn-success'
				method='post'>
				<br> <input type='submit' value='KULLANICI ISLEMLERI'>
			</form>
			<br> <br>
			
			

			<%
				}
			%>

		</div>
	</div>
	<div id="sagparca">
		<form action="yemek_sepetim.jsp" method="get">
			<table class="table table-bordered">
				<tr>
					<td class="tr1td1">Yiyecekler :<select multiple
						class="form-control" id="yiyeceklerkutusu">
							<%
								for (int i = 0; i < tb.YiyeceklerListesi.size(); i++) {
									String yemekAd = tb.YiyeceklerListesi.get(i);
									int fiyat = tb.yemekMap.get(tb.YiyeceklerListesi.get(i));
							%>
							<option value="<%=yemekAd%>"><%=yemekAd + " " + fiyat + " TL"%></option>
							<%
								System.out.println(yemekAd + " " + fiyat + " TL");
								}
							%>
					</select><br> Adet :<input type="text" name='yemekleradet'
						id='yemekadetkutusu'>
						<button type='button' class="btn btn-success" value='tamam'
							onclick="yemekleriYazdirma()">Tamam</button>
					</td>
					<td class="tr1td2">Secilen Yiyecekler : <textarea
							class="form-control" rows="3" name='secilenyemekler'
							id='secilenyemeklertexti' width='200'></textarea> Toplam : <input
						type='text' name='yemektexti' id='toplamyemeklertexti'>


					</td>
				</tr>
				<tr>
					<td class=tr2td1>Icecekler :<select multiple
						class="form-control" id="iceceklerkutusu">
							<%
								for (int i = 0; i < tb.IceceklerListesi.size(); i++) {
									String icecek = tb.IceceklerListesi.get(i);
									int fiyat = tb.icecekMap.get(tb.IceceklerListesi.get(i));
							%>
							<option value="<%=icecek%>"><%=icecek + " " + fiyat + " TL"%></option>
							<%
								}
							%>
					</select><br> Adet : <input type='text' name='icecekleradet'
						id='icecekadetkutusu'>
						<button type='button' class='btn btn-success' value='Tamam'
							onclick="icecekleriYazdirma()">Tamam</button>
					</td>
					<td class=tr2td2>Secilen Icecekler : <textarea
							class=form-control rows=3 name='secilenicecekler'
							id='secileniceceklertexti'></textarea> Toplam : <input
						type='text' name='icecektexti' id='toplamiceceklertexti'>
					</td>
				</tr>
				<tr>
					<td class=tr3td1>Salatalar : <select multiple
						class="form-control" id="salatalarkutusu">
							<%
								for (int i = 0; i < tb.SalatalarListesi.size(); i++) {
									String salata = tb.SalatalarListesi.get(i);
									int fiyat = tb.salataMap.get(tb.SalatalarListesi.get(i));
							%>


							<option value="<%=salata%>"><%=salata + " " + fiyat + " TL"%></option>
							<%
								}
							%>
					</select><br> Adet : <input type='text' name='salatalaradet'
						id='salataadetkutusu'>
						<button type='button' class='btn btn-success' value='Tamam'
							onclick="salatalariYazdirma()">Tamam</button>
					</td>
					<td class=tr3td2>Secilen Salatalar : <textarea
							class=form-control name='secilensalatalar'
							id='secilensalatalartexti'></textarea> Toplam : <input
						type='text' name='salatatexti' id='toplamsalatalartexti'>
					</td>
				<tr>
				<tr>
					<td class=tr3td1>Tatlilar : <select multiple
						class="form-control" id="tatlilarkutusu">
							<%
								for (int i = 0; i < tb.TatlilarListesi.size(); i++) {
									String tatli = tb.TatlilarListesi.get(i);
									int fiyat = tb.tatliMap.get(tb.TatlilarListesi.get(i));
							%>
							<option value="<%=tatli%>"><%=tatli + " " + fiyat + " TL"%></option>
							<%
								}
							%>
					</select> <br> Adet : <input type='text' name='tatlilaradet'
						id='tatliadetkutusu'>
						<button type='button' class='btn btn-success' value='Tamam'
							onclick="tatlilariYazdirma()">Tamam</button>
					</td>
					<td class=tr3td2>Secilen Tatlilar : <textarea
							class=form-control name='secilentatlilar'
							id='secilentatlilartexti'></textarea> Toplam : <input type='text'
						name='tatlitexti' id='toplamtatlilartexti'>
					</td>
				<tr>
					<td>Toplam Tutar : <input type='text' name='geneltoplam'
						id='geneltoplamtexti'>
					</td>
					<td>
						<button type='button' class='btn btn-success' value='Temizle'
							onclick='temizle()'>Temizle</button>
					</td>
				</tr>
				<tr>
					<!-- Siparisi Onaylar ise Siparis'i yazdirmak icin
						SiparisleriAl servlet'ine gidiliyor. -->

					<td colspan=2><input type='submit' class='btn btn-success'
						value='Siparisi Onayla'></td>
				</tr>
			</table>
		</form>

		<div id=altparca>Copyright © McanMurat</div>
	</div>
</body>
</html>