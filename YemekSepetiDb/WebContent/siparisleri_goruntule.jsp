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
</head>
<body>

	<h2>SIPARISLER</h2>

	<table width="736" height="97" border="1" class="table table-bordered">
		
		
	
		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong>SIPARIS ID</strong>
				</div></td>
			<td><div align="center">
					<strong>KULLANICI ID</strong>
				</div></td>
			<td><div align="center">
					<strong>YEMEKLER</strong>
				</div></td>
			<td><div align="center">
					<strong>ICECEKLER</strong>
				</div></td>
			<td><div align="center">
					<strong>SALATALAR</strong>
				</div></td>
			<td><div align="center">
					<strong>TATLILAR</strong>
				</div></td>
			<td><div align="center">
					<strong>SIPARIS TARIHI</strong>
				</div></td>
			

		</tr>
		<%
			int icount = 0;
			rs = stmt.executeQuery("select * from siparisler");
			while (rs.next()) {
		%>
		<tr>

			<td><%=rs.getInt("SiparisId")%></td>
			<td><%=session.getAttribute("kullaniciAdi")%></td>
			<td><%=rs.getString("Yemekler")%></td>
			<td><%=rs.getString("Icecekler")%></td>
			<td><%=rs.getString("Salatalar")%></td>
			<td><%=rs.getString("Tatlilar")%></td>
			<td><%=rs.getString("Tarih")%></td>

		</tr>
		<%
			}
		%>
	</table>
	<% session.setAttribute("stilladmin",true); %>
	<a href="SiparisSayfa.jsp">SIPARIS SAYFASI</a>

</body>
</html>

