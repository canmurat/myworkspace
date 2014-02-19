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
	function validate(salata) {
		if (salata.salataAd.value.length == 0) {
			alert("Lutfen salata ismi giriniz !");
			salata.salataAd.focus();
			return false;
		}

		if (salata.salataFiyat.value.length == 0) {
			alert("Lutfen fiyat giriniz !");
			salata.salataFiyat.focus();
			return false;
		}
		
		return true;
	}
</script>


</head>
<body>   

	<h2>SALATA EKLE</h2>
	<form id="salata" action="db_salata_ekle.jsp"
		method="post" onSubmit="return validate(this)">
		<table width="332" height="252" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<th height="33" colspan="2"><div align="center">
						<%
						urun3 = (String) session.getAttribute("salata");
							session.removeAttribute("salata");
							if (urun3 != null)
								out.print(urun3);
						%>
					</div></th>
			</tr>
			<tr>
				<th>SALATA ADI</th>
				<td><input name="salataAdi" type="text" class="text"
					id="salataAdi" value="" /></td>
			</tr>
			<tr>
				<th>SALATA FIYATI</th>
				<td><input name="salataFiyati" type="text" class="text"
					id="salataFiyati" value="" /></td>
			</tr>

			<tr>
				<td class="submission" colspan="2"><div align="center">
						<input name="s" type="submit" class="button" value="EKLE" /> <input
							name="Reset" type="reset" class="button" value="YENILE" />
					</div></td>
			</tr>
			<tr>
				<th class="submission" colspan="2"><div align="center">
						<a href="salata_goruntule.jsp">salataleri Goruntule</a>
					</div></th>
			</tr>
		</table>

	</form>


</body>
</html>
