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
		icecekId1 = request.getParameter("icecekId");
		if (icecekId1 != null)
			icecekId = Integer.parseInt(icecekId1);
		icecegi_sil = request.getParameter("icecegi_sil");

		if ("yes".equals(icecegi_sil)) {
			icecekAd = request.getParameter("icecekAd");
			x = stmt1
					.executeUpdate("Delete from icecekler where Id="
							+ icecekId);
		}
	%>


	<h2>ICECEKLER</h2>

	<table width="736" height="97" border="1" class="table table-bordered">
		<%
			if (x == 1) {
		%>
		<tr bgcolor="#000000">
			<th height="35" colspan="9"><div align="center">
					icecekAd (<%=icecekAd%>) deleted successfully
				</div></th>
		</tr>
		<%
			}
		%>
		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong>ICECEK ID</strong>
				</div></td>
			<td><div align="center">
					<strong>ICECEK ADI</strong>
				</div></td>
			<td><div align="center">
					<strong>ICECEK FIYATI (TL)</strong>
				</div></td>
			
		</tr>
		<%
			int icount = 0;
			rs = stmt.executeQuery("select * from icecekler");
			while (rs.next()) {
				icecekAd = rs.getString("Ad");
				icecekFiyat = rs.getInt("Fiyat");
				icecekId = rs.getInt("Id");
				
		%>
		<tr>
			<td><div align="center">
			<%=icecekId%></div></td>
			<td><%=icecekAd%></td>
			<td><%=icecekFiyat%></td>
			
			<td><div align="center">
					<a href="icecek_duzenle.jsp?icecekId=<%=icecekId%>">Edit</a>
				</div></td>
			<td><div align="center">
					<a
						href="icecek_goruntule.jsp?icecegi_sil=yes&icecekId=<%=icecekId%>&icecekAd=<%=icecekAd%>"
						onclick="return del()">Delete</a>
				</div></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="icecek_ekle.jsp">ICECEK EKLE</a>
	<a href="SiparisSayfa.jsp">ANA SAYFA</a>
	
	<% session.setAttribute("stilladmin", true); %>

</body>
</html>

