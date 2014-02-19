<%@include file="include/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Food Online</title>
<link rel="stylesheet" href="images/style.css" type="text/css"
	charset="utf-8" />
</head>
<body>
	<%
		KullaniciId1 = request.getParameter("KullaniciId");
		out.println(KullaniciId1 + " kullaniciId1");
		if (KullaniciId1 != null)
			KullaniciId = Integer.parseInt(KullaniciId1);
		out.println(KullaniciId + " kullaniciId");
		rs = stmt.executeQuery("Select * from kullanicilar where Id="
				+ KullaniciId);
		if (rs.next()) {
			Ad = rs.getString("Ad");
			Soyad = rs.getString("Soyad");
			DTarih = rs.getString("DTarih");
			Adres = rs.getString("Adres");
			EPosta = rs.getString("EPosta");
			Sifre = rs.getLong("Sifre");
			Tel = rs.getInt("Tel");
			Admin = rs.getInt("Admin");
			KrediKartNo = rs.getInt("KrediKartNo");
		}
	%>

	<h2>KULLANICILARI DUZENLE</h2>
	<form  action="kullanici_guncelle.jsp"
		method="post">
		<table width="332" height="252" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<th height="33" colspan="2"><div align="center">
						<%
							guncel_kullanici = (String) session
									.getAttribute("guncel_kullanici");
							session.removeAttribute("guncel_kullanici");
							if (guncel_kullanici != null)
								out.print(guncel_kullanici);
						%>
					</div></th>
			</tr>
			<tr>
				<th>Ad</th>
				<td><input name="Ad" type="text" class="text" id="Ad"
					value="<%=Ad%>" /></td>
			</tr>
			<tr>
				<th>Soyad</th>
				<td><input name="Soyad" type="text" class="text" id="Soyad"
					value="<%=Soyad%>" /></td>
			</tr>
			<tr>
				<th>Dogum Tarihi</th>
				<td><input name="DTarih" type="text" class="text" id="DTarih"
					value="<%=DTarih%>" /></td>
			</tr>
			<tr>
				<th>Adres</th>
				<td><input name="Adres" type="text" class="text" id="Adres"
					value="<%=Adres%>" /></td>
			</tr>

			<tr>
				<th>E-Posta</th>
				<td><input name="EPosta" type="text" class="text" id="EPosta"
					value="<%=EPosta%>" /></td>
			</tr>
			<tr>
				<th>Sifre</th>
				<td><input name="Sifre" type="password" class="text" id="Sifre"
					value="<%=Sifre%>" /></td>
			</tr>
			<tr>
			<tr>
				<th>Telefon</th>
				<td><input name="Tel" type="text" class="text" id="Tel"
					value="<%=Tel%>" /></td>
			</tr>
			<tr>
			<th>Admin</th>
			<td><input name="Admin" type="text" class="text" id="Admin"
				value="<%=Admin%>" /></td>
			</tr>
			<th>Kredi Kart No</th>
			<td><input name="KrediKartNo" type="password" class="text" id="KrediKartNo"
				value="<%=Admin%>" /></td>
			</tr>
			

			<tr>
				<th class="submission" colspan="2"><div align="center">
						<a href="kullanici_goruntule.jsp">Kullanicilari Goruntule</a>
					</div></th>
			</tr>
			<tr>
				<td></td>
				<td class="submission" colspan="6"><input type="hidden"
					name="KullaniciId" value="<%=KullaniciId%>" /> <input name="s"
					type="submit" class="button" value="GUNCELLE" /></td>
			</tr>
		</table>
	</form>

</body>
</html>
