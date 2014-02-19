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
<!--  Boot Strap ve css dosyalarini import ediyorum -->
</head>
<body>
	<%
		yemekId1 = request.getParameter("yemekId");
		if (yemekId1 != null)
			yemekId = Integer.parseInt(yemekId1);
		yemegi_sil = request.getParameter("yemegi_sil");

		
		// Yiyecek basari ile silinir ise x degiskenine sorgu sonucu olarak 1 degeri atılır.
		if ("yes".equals(yemegi_sil)) {
			yemekAd = request.getParameter("yiyecekAd");
			x = stmt1
					.executeUpdate("Delete from yiyecekler where Id="
							+ yemekId);
		}
	%>


	<h2>YIYECEKLER</h2>

	<table width="736" height="97" border="1" class="table table-bordered">
		<%  // x degiskeninin degeri 1 olmus ise silinme islemi basarili demektir. 
			if (x == 1) {
		%>
		<tr bgcolor="#000000">
			<th height="35" colspan="9"><div align="center">
					Yemek (<%=yemekAd%>) basari ile silindi.
				</div></th>
		</tr>
		<%
			}
		%>
		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong>YIYECEK ID</strong>
				</div></td>
			<td><div align="center">
					<strong>YIYECEK ADI</strong>
				</div></td>
			<td><div align="center">
					<strong>YIYECEK FIYATI (TL)</strong>
				</div></td>
			
		</tr>
		<%
			int icount = 0;
			rs = stmt.executeQuery("select * from yiyecekler");
			while (rs.next()) {
				yemekAd = rs.getString("Ad");
				yemekFiyat = rs.getInt("Fiyat");
				yemekId = rs.getInt("Id");
// Veritabanı sonuclarını dongu halinde tabloya ekliyorum.				
		%>
		<tr>
			<td><div align="center">
			<%=yemekId%></div></td>
			<td><%=yemekAd%></td>
			<td><%=yemekFiyat%></td>
			
			<td><div align="center">
					<a href="yiyecek_duzenle.jsp?yemekId=<%=yemekId%>">Edit</a>
				</div></td>
			<td><div align="center">
			<!--  Silme islemi yapılacagı zaman yemegi_sil degiskeni yes yapılıyor. ve silme saglanıyor. -->
					<a href="yiyecek_goruntule.jsp?yemegi_sil=yes&yemekId=<%=yemekId%>&yemekAd=<%=yemekAd%>"
						onclick="return del()">Delete</a>
				</div></td>
		</tr>
		<%
			}
		%>
	</table>
	<!--  Yonlendirme Linkleri
	 -->
	<a href="yiyecek_ekle.jsp">YIYECEK EKLE</a>
	<a href="SiparisSayfa.jsp">ANA SAYFA</a>
	
	<!--  Admin iken bu sayfalara gelinip gidilirken hala adminn olmasını saglamak icin attribute konuldu -->
	<% session.setAttribute("stilladmin", true); %>
</body>
</html>

