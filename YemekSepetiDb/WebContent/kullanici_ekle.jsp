<%@ include file="include/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="images/style.css" type="text/css"
	charset="utf-8" />


<!-- Submit butonuna bastıgı anda alanların bos olup olmadıgını kontrol eden fonksiyon -->
<!-- Alanlar eger bos ise uyarı gonderiyor ve girdi istiyor. -->
<script language="javascript">
	function validate(kullanici) {
		if (kullanici.Ad.value.length == 0) {
			alert("Lutfen kullanici ismi giriniz !");
			kullanici.Ad.focus();
			return false;
		}

		if (kullanici.Soyad.value.length == 0) {
			alert("Lutfen Soyad giriniz !");
			kullanici.Soyad.focus();
			return false;
		}
		if (kullanici.DTarih.value.length == 0) {
			alert("Lutfen Dogum Tarihi giriniz !");
			kullanici.DTarih.focus();
			return false;
		}
		if (kullanici.Adres.value.length == 0) {
			alert("Lutfen Adres giriniz !");
			kullanici.Adres.focus();
			return false;
		}
		if (kullanici.EPosta.value.length == 0) {
			alert("Lutfen E-Posta giriniz !");
			kullanici.EPosta.focus();
			return false;
		}
		if (kullanici.Sifre.value.length == 0) {
			alert("Lutfen Sifre giriniz !");
			kullanici.EPosta.focus();
			return false;
		}
		if (kullanici.Tel.value.length == 0) {
			alert("Lutfen Telefon giriniz !");
			kullanici.Tel.focus();
			return false;
		}
		if (kullanici.Admin.value.length == 0) {
			alert("Lutfen Admin durumunu 1 ya da 0 olarak giriniz !");
			kullanici.Admin.focus();
			return false;
		}
		if (kullanici.KrediKartNo.value.length == 0) {
			alert("Lütfen Kredi Kart bilgisini giriniz ! !");
			kullanici.KrediKartNo.focus();
			return false;
		}
		
		
		return true;
	}
</script>


</head>
<body>   

	<h2>KULLANICI EKLE</h2>
	<form id="kullanici" action="db_kullanici_ekle.jsp"
		method="post" onSubmit="return validate(this)">
		<table width="332" height="252" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<th height="33" colspan="2"><div align="center">
						<%
						kullanici = (String) session.getAttribute("kullanici");
							session.removeAttribute("kullanici");
							if (kullanici != null)
								out.print(kullanici);
						%>
					</div></th>
			</tr>
			<tr>
				<th>KULLANICI ADI</th>
				<td><input name="Ad" type="text" class="text"
					id="Ad" value="" /></td>
			</tr>
			<tr>
				<th>KULLANICI SOYADI</th>
				<td><input name="Soyad" type="text" class="text"
					id="Soyad" value="" /></td>
			</tr>
			<tr>
				<th>KULLANICI DOGUM TARIHI</th>
				<td><input name="DTarih" type="text" class="text"
					id="DTarih" value="" /></td>
			</tr>
			<tr>
				<th>KULLANICI ADRES</th>
				<td><input name="Adres" type="text" class="text"
					id="Adres" value="" /></td>
			</tr>
			<tr>
				<th>KULLANICI EPOSTA</th>
				<td><input name="EPosta" type="text" class="text"
					id="EPosta" value="" /></td>
			</tr>
			<tr>
				<th>KULLANICI TEL</th>
				<td><input name="Tel" type="text" class="text"
					id="Tel" value="" /></td>
			</tr>
			<tr>
				<th>SIFRE</th>
				<td><input name="Sifre" type="password" class="text"
					id="Sifre" value="" /></td>
			</tr>
			
			<tr>
				<th>KULLANICI KART NO</th>
				<td><input name="KrediKartNo" type="password" class="text"
					id="KrediKartNo" value="" /></td>
			</tr>
			<tr>
				<th>KULLANICI ADMIN</th>
				<td><input name="Admin" type="text" class="text"
					id="Admin" value="" /></td>
			</tr>
			

			<tr>
				<td class="submission" colspan="2"><div align="center">
						<input name="s" type="submit" class="button" value="EKLE" /> <input
							name="Reset" type="reset" class="button" value="YENILE" />
					</div></td>
			</tr>
			<tr>
				<th class="submission" colspan="2"><div align="center">
						<a href="kullanici_goruntule.jsp">kullanicileri Goruntule</a>
					</div></th>
			</tr>
		</table>

	</form>


</body>
</html>
