<%@include file="include/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javascript">
	function del() {
		if (confirm("Tatliyi silmek istediginizden emin misiniz ?")) {
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
		tatliId1 = request.getParameter("tatliId");
		if (tatliId1 != null)
			tatliId = Integer.parseInt(tatliId1);
		tatliyi_sil = request.getParameter("tatliyi_sil");

		if ("yes".equals(tatliyi_sil)) {
			tatliAd = request.getParameter("tatliAd");
			x = stmt1
					.executeUpdate("Delete from tatlilar where Id="
							+ tatliId);
		}
	%>


	<h2> TATLILAR </h2>

	<table width="736" height="97" border="1" class="table table-bordered">
		<%
			if (x == 1) {
		%>
		<tr bgcolor="#000000">
			<th height="35" colspan="9"><div align="center">
					Tatli (<%=tatliAd%>) basari ile silindi.
				</div></th>
		</tr>
		<%
			}
		%>
		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong>TATLI ID</strong>
				</div></td>
			<td><div align="center">
					<strong>TATLI ADI</strong>
				</div></td>
			<td><div align="center">
					<strong>TATLI FIYATI (TL)</strong>
				</div></td>
			
		</tr>
		<%
			int icount = 0;
			rs = stmt.executeQuery("select * from tatlilar");
			while (rs.next()) {
				tatliAd = rs.getString("Ad");
				tatliFiyat = rs.getInt("Fiyat");
				tatliId = rs.getInt("Id");
				
		%>
		<tr>
			<td><div align="center">
			<%=tatliId%></div></td>
			<td><%=tatliAd%></td>
			<td><%=tatliFiyat%></td>
			
			<td><div align="center">
					<a href="tatli_duzenle.jsp?tatliId=<%=tatliId%>">Edit</a>
				</div></td>
			<td><div align="center">
			  <!--  tatliyi silebilmesi icin tatliyi_sil adındaki degiskene 'yes' degerini atıyoruz -->
					<a    
						href="tatli_goruntule.jsp?tatliyi_sil=yes&tatliId=<%=tatliId%>&tatliAd=<%=tatliAd%>"
						onclick="return del()">Sil </a>
				</div></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="tatli_ekle.jsp">Tatli Ekle</a>
	<a href="SiparisSayfa.jsp">ANA SAYFA</a>
	<% session.setAttribute("stilladmin", true); %>
</body>
</html>

