


<%@page import="com.sepetim.SiparisleriYazdir"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.RequestDispatcher"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@include file="include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Food Online</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>


	<%
		if (request.getParameter("secilenyemekler") != null
				|| request.getParameter("yemektexti") != null) {

			session.setAttribute("secilenyemekler",
					request.getParameter("secilenyemekler"));
			session.setAttribute("yemeklertoplam",
					request.getParameter("yemektexti"));
		}
		if (request.getParameter("secilenicecekler") != null
				|| request.getParameter("icecektexti") != null) {
	System.out.println("request.getParameter(\"icecektexti\")" + request.getParameter("icecektexti"));
			session.setAttribute("secilenicecekler",
					request.getParameter("secilenicecekler"));
			session.setAttribute("iceceklertoplam",
					request.getParameter("icecektexti"));
		}
		if (request.getParameter("secilensalatalar") != null
				|| request.getParameter("salatatexti") != null) {

			session.setAttribute("secilensalatalar",
					request.getParameter("secilensalatalar"));
			session.setAttribute("salatalartoplam",
					request.getParameter("salatatexti"));
		}
		if (request.getParameter("secilentatlilar") != null
				|| request.getParameter("tatlitexti") != null) {

			session.setAttribute("secilentatlilar",
					request.getParameter("secilentatlilar"));
			session.setAttribute("tatlilartoplam",
					request.getParameter("tatlitexti"));
		}
	%>

	<%
		alinanyemekler = (String) session.getAttribute("secilenyemekler");
		yemeklertoplam1 = String.valueOf(session.getAttribute("yemeklertoplam"));
	%>
	<%
		alinanicecekler = (String) session.getAttribute("secilenicecekler");
		iceceklertoplam1 = String.valueOf(session.getAttribute("iceceklertoplam"));
	%>
	<%
		alinansalatalar = (String) session.getAttribute("secilensalatalar");
		salatalartoplam1 = String.valueOf(session.getAttribute("salatalartoplam"));
	%>
	<%
		alinantatlilar = (String) session.getAttribute("secilentatlilar");
		tatlilartoplam1 = String.valueOf(session.getAttribute("tatlilartoplam"));
	%>


	<%-- <jsp:useBean id="myBean" class="com.sepetim.BeanSepetim" scope="session"> --%>
	<%-- </jsp:useBean> --%>
	<%-- <jsp:setProperty name="myBean" property="alinanyemekler" --%>
	<%-- 	value="<%=request.getParameter("secilenyemekler")%>" /> --%>
	<%-- <jsp:setProperty name="myBean" property="alinanicecekler" --%>
	<%-- 	value="<%=request.getParameter("secilenicecekler")%>" /> --%>
	<%-- <jsp:setProperty name="myBean" property="alinansalatalar" --%>
	<%-- 	value="<%=request.getParameter("secilensalatalar")%>" /> --%>
	<%-- <jsp:setProperty name="myBean" property="alinantatlilar" --%>
	<%-- 	value="<%=request.getParameter("secilentatlilar")%>" /> --%>

	<%-- <jsp:setProperty name="myBean" property="yemeklertoplam" --%>
	<%-- 	value="<%=request.getParameter("yemektexti")%>" /> --%>
	<%-- <jsp:setProperty name="myBean" property="iceceklertoplam" --%>
	<%-- 	value="<%=request.getParameter("icecekletexti")%>" /> --%>
	<%-- <jsp:setProperty name="myBean" property="salatalartoplam" --%>
	<%-- 	value="<%=request.getParameter("salatalartexti")%>" /> --%>
	<%-- <jsp:setProperty name="myBean" property="tatlilartoplam" --%>
	<%-- 	value="<%=request.getParameter("tatlilartexti")%>" /> --%>



	<%
		// 	SiparisleriYazdir sp = new SiparisleriYazdir();
		// 	alinanyemekler = 
		// 	alinanicecekler = request.getParameter("secilenicecekler");
		// 	alinantatlilar = request.getParameter("secilentatlilar");
		// 	alinansalatalar = request.getParameter("secilensalatalar");

		// // 	toplamtutar = Integer.valueOf(request.getParameter("geneltoplam"));

		// 	yemeklertoplam = Integer.valueOf(request.getParameter("yemektexti"));
		// 	iceceklertoplam = Integer.valueOf(request.getParameter("icecektexti"));
		// 	salatalartoplam = Integer.valueOf(request.getParameter("salatatexti"));
		// 	tatlilartoplam = Integer.valueOf(request.getParameter("tatlitexti"));

		kullanici = (String) session.getAttribute("kullaniciAdi");
	%>


	<script type="text/javascript">
	
	// yemegi silmek istediginizden emin misiniz soruları soruluyor ona gore silme islemi gerceklesiyor.
	
		function del_yemekler() {

			if (confirm("Yemegi silmek istediginizden emin misiniz ?")) {
			} else {
				return false;
			}
		}

		function del_icecekler() {
			if (confirm("Icecegi silmek istediginizden emin misiniz ?")) {
			} else {
				return false;
			}
		}
		function del_salatalar() {
			if (confirm("Salatayi silmek istediginizden emin misiniz ?")) {
			} else {
				return false;
			}
		}
		function del_tatlilar() {
			if (confirm("Tatliyi silmek istediginizden emin misiniz ?")) {

			} else {
				return false;
			}
		}
	</script>

	<% // degerler ilk basta String olarak alinip sonra int degiskenlere  aktariliyor.
		if (yemeklertoplam1 != null)
			yemeklertoplam = Integer.parseInt(yemeklertoplam1);

		if (iceceklertoplam1 != null)
			iceceklertoplam = Integer.parseInt(iceceklertoplam1);

		if (salatalartoplam1 != null)
			salatalartoplam = Integer.parseInt(salatalartoplam1);

		if (tatlilartoplam1 != null)
			tatlilartoplam = Integer.parseInt(tatlilartoplam1);

		sepet_yemek_sil = request.getParameter("sepet_yemek_sil");

		// Yiyecek basari ile silinir ise x degiskenine sorgu sonucu olarak 1 degeri atılır.
		if ("yes".equals(sepet_yemek_sil)) {
			alinanyemekler = "";
			yemeklertoplam = 0;
			session.setAttribute("secilenyemekler", alinanyemekler);
			session.setAttribute("yemeklertoplam",
					String.valueOf(yemeklertoplam));

			sepet_yemek_sil = "no";
		}
		sepet_icecek_sil = request.getParameter("sepet_icecek_sil");

		// Yiyecek basari ile silinir ise x degiskenine sorgu sonucu olarak 1 degeri atılır.
		if ("yes".equals(sepet_icecek_sil)) {
			alinanicecekler = "";
			iceceklertoplam = 0;
			sepet_icecek_sil = "no";
			session.setAttribute("secilenicecekler", alinanicecekler);
			session.setAttribute("iceceklertoplam",
					String.valueOf(iceceklertoplam));
		}
		sepet_salata_sil = request.getParameter("sepet_salata_sil");

		// Yiyecek basari ile silinir ise x degiskenine sorgu sonucu olarak 1 degeri atılır.
		if ("yes".equals(sepet_salata_sil)) {
			alinansalatalar = "";
			salatalartoplam = 0;
			sepet_salata_sil = "no";
			session.setAttribute("secilensalatalar", alinansalatalar);
			session.setAttribute("salatalartoplam",
					String.valueOf(salatalartoplam));
		}
		sepet_tatli_sil = request.getParameter("sepet_tatli_sil");

		// Yiyecek basari ile silinir ise x degiskenine sorgu sonucu olarak 1 degeri atılır.
		if ("yes".equals(sepet_tatli_sil)) {
			alinantatlilar = "";
			tatlilartoplam = 0;
			sepet_tatli_sil = "no";
			session.setAttribute("secilentatlilar", alinantatlilar);
			session.setAttribute("tatlilartoplam",
					String.valueOf(tatlilartoplam));
		}
	%>
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
	<table id="mytable" width="736" height="97" border="1"
		class="table table-bordered">

		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong></strong>
				</div></td>
			<td><div align="center">
					<strong>ALDIKLARIM</strong>
				</div></td>
			<td><div align="center">
					<strong>TOPLAM FIYAT</strong>
				</div></td>
			<td><div align="center">
					<strong></strong>
				</div></td>

		</tr>
		<%
			
		%>
		<tr>
			<td><div align="center">
					<strong>YEMEKLER</strong>
				</div></td>
			<td name="yemekler"><%=alinanyemekler%></td>
			<td><%=yemeklertoplam%></td>

			<td><div align="center">
					<a href="yemek_sepetim.jsp?sepet_yemek_sil=yes"
						onclick="return del_yemekler()">Delete</a>
				</div></td>
		</tr>
		<tr>
			<td><div align="center">
					<strong>ICECEKLER</strong>
				</div></td>
			<td name="icecekler"><%=alinanicecekler%></td>
			<td><%=iceceklertoplam%></td>

			<td><div align="center">
					<a href="yemek_sepetim.jsp?sepet_icecek_sil=yes"
						onclick="return del_icecekler()">Delete</a>
				</div></td>
		</tr>
		<tr>
			<td><div align="center">
					<strong>SALATALAR</strong>
				</div></td>
			<td name="salatalar"><%=alinansalatalar%></td>
			<td><%=salatalartoplam%></td>

			<td><div align="center">
					<a href="yemek_sepetim.jsp?sepet_salata_sil=yes"
						onclick="return del_salatalar()">Delete</a>
				</div></td>
		</tr>
		<tr>
			<td><div align="center">
					<strong>TATLILAR</strong>
				</div></td>
			<td name="tatlilar"><%=alinantatlilar%></td>
			<td><%=tatlilartoplam%></td>

			<td><div align="center">
					<a href="yemek_sepetim.jsp?sepet_tatli_sil=yes"
						onclick="return del_tatlilar()">Delete</a>
				</div></td>
		</tr>
		<%
			session.setAttribute("alinanyemekler", alinanyemekler);
			session.setAttribute("alinanicecekler", alinanicecekler);
			session.setAttribute("alinansalatalar", alinansalatalar);
			session.setAttribute("alinantatlilar", alinantatlilar);
			session.setAttribute("yemeklertoplam", yemeklertoplam);
			session.setAttribute("iceceklertoplam", iceceklertoplam);
			session.setAttribute("salatalartoplam", salatalartoplam);
			session.setAttribute("tatlilartoplam", tatlilartoplam);
		%>
	</table>
	
	<div id='kredikart'></div>
	
	<form action="OdemeYap.jsp">
		<table id="mytable2" border="1" cellpadding="2" cellspacing="2"
			class="table table-bordered">

			<tr>
				<th>Kredi Kart No :</th>
				<td><input name="KrediKartNo" type="text" class="text"
					id="KrediKartNo" value="" class="form-control" /></td>
			</tr>
			<br>
				<tr>
					<br>
						<th>Guvenlik Kodu :</th>
						<td><input name="GuvenlikKodu" type="text" class="text"
							id="GuvenlikKodu" value="" class="form-control" /></td>
				</tr>
				<tr>
					

					<th></th>
					<th><input type="submit" value="Odeme Yap"
						class="btn btn-success"></input></th>
				</tr>
		</table>
		
		<%
						if (session.getAttribute("odeme") != null) {
					%>
					<br> <h3><%=session.getAttribute("odeme")%></h3>
					<%
						session.removeAttribute("odeme");
						}
					%>
	</form>
</body>
</html>