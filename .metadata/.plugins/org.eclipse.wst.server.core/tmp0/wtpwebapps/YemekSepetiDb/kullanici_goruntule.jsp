<%@include file="include/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javascript">
	function del() {
		if (confirm("Yemegi silmek istediginizden emin misiniz ?")) {
		} else {
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
		KullaniciId1 = request.getParameter("KullaniciId");
		if (KullaniciId1 != null)
			KullaniciId = Integer.parseInt(KullaniciId1);
		kullanici_sil = request.getParameter("kullanici_sil");

		if ("yes".equals(kullanici_sil)) {
			Ad = request.getParameter("Ad");
			x = stmt1
					.executeUpdate("Delete from kullanicilar where Id="
							+ KullaniciId1);
		}
	%>


	<h2>KULLANICILAR</h2>

	<table width="736" height="97" border="1" class="table table-bordered">
		<%
			if (x == 1) {
		%>
		<tr bgcolor="#000000">
			<th height="35" colspan="9"><div align="center">
					Kullanici (<%=Ad%>) basari ile silindi.
				</div></th>
		</tr>
		<%
			}
		%>
		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong>ID</strong>
				</div></td>
			<td><div align="center">
					<strong>ADI</strong>
				</div></td>
			<td><div align="center">
					<strong>SOYADI</strong>
				</div></td>
				<td><div align="center">
					<strong>DOGUM TARIHI</strong>
				</div></td>
				<td><div align="center">
					<strong>ADRES</strong>
				</div></td>
				<td><div align="center">
					<strong>EPOSTA</strong>
				</div></td>
				<td><div align="center">
					<strong>SIFRE</strong>
				</div></td>
				<td><div align="center">
					<strong>TELEFON</strong>
				</div></td>
				<td><div align="center">
					<strong>ADMIN</strong>
				</div></td>
				<td><div align="center">
					<strong>KREDI KART NO</strong>
				</div></td>
			
		</tr>
		<%
			int icount = 0;
			rs = stmt.executeQuery("select * from kullanicilar");
			while (rs.next()) {
				KullaniciId = rs.getInt("Id");
				Ad = rs.getString("Ad");
				Soyad = rs.getString("Soyad");
				DTarih = rs.getString("DTarih");
				Adres = rs.getString("Adres");
				EPosta = rs.getString("EPosta");
				Sifre =rs.getLong("Sifre");
				Tel = rs.getLong("Tel");
				Admin = rs.getInt("Admin");
				KrediKartNo =rs.getLong("KrediKartNo");
			
		
		
				
		%>
		<tr>
			<td><div align="center">
			<%=KullaniciId%></div></td>
			<td><%=Ad%></td>
			<td><%=Soyad%></td>
			<td><%=DTarih%></td>
			<td><%=Adres%></td>
			<td><%=EPosta%></td>
			<td><%=Sifre%></td>
			<td><%=Tel%></td>
			<td><%=Admin%></td>
			<td><%=KrediKartNo%></td>
			
		
			
			
			<td><div align="center">
					<a href="kullanici_duzenle.jsp?KullaniciId=<%=KullaniciId%>">Edit</a>
				</div></td>
			<td><div align="center">
					<a
						href="kullanici_goruntule.jsp?kullanici_sil=yes&KullaniciId=<%=KullaniciId%>&Ad=<%=Ad%>"
						onclick="return del()">SIL</a>
				</div></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="kullanici_ekle.jsp">KULLANICI EKLE</a>
	<a href="SiparisSayfa.jsp">ANA SAYFA</a>
	<% session.setAttribute("stilladmin", true); %>

</body>
</html>

