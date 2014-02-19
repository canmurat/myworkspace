<%@include file="include/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javascript">
	function del() {
		if (confirm("Salatayı silmek istediginizden emin misiniz ?")) {
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
		salataId1 = request.getParameter("salataId");
		if (salataId1 != null)
			salataId = Integer.parseInt(salataId1);
		salatayi_sil = request.getParameter("salatayi_sil");

		if ("yes".equals(salatayi_sil)) {
			salataAd = request.getParameter("salataAd");
			x = stmt1
					.executeUpdate("Delete from salatalar where Id="
							+ salataId);
		}
	%>


	<h2>SALATALAR</h2>

	<table width="736" height="97" border="1" class="table table-bordered">
		<%
			if (x == 1) {
		%>
		<tr bgcolor="#000000">
			<th height="35" colspan="9"><div align="center">
					Salata (<%=salataAd%>) basari ile silindi.
				</div></th>
		</tr>
		<%
			}
		%>
		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong>SALATA ID</strong>
				</div></td>
			<td><div align="center">
					<strong>SALATA ADI</strong>
				</div></td>
			<td><div align="center">
					<strong>SALATA FIYATI (TL)</strong>
				</div></td>
			
		</tr>
		<%
			int icount = 0;
			rs = stmt.executeQuery("select * from salatalar");
			while (rs.next()) {
				salataAd = rs.getString("Ad");
				salataFiyat = rs.getInt("Fiyat");
				salataId = rs.getInt("Id");
				
		%>
		<tr>
			<td><div align="center">
			<%=salataId%></div></td>
			<td><%=salataAd%></td>
			<td><%=salataFiyat%></td>
			
			<td><div align="center">
					<a href="salata_duzenle.jsp?salataId=<%=salataId%>">Edit</a>
				</div></td>
			<td><div align="center">
					<a
						href="salata_goruntule.jsp?salatayi_sil=yes&salataId=<%=salataId%>&salataAd=<%=salataAd%>"
						onclick="return del()">Delete</a>
				</div></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="salata_ekle.jsp">SALATA EKLE</a>
	<a href="SiparisSayfa.jsp">ANA SAYFA</a>
	<% session.setAttribute("stilladmin", true); %>
</body>
</html>

