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
	function validate(icecek) {
		if (icecek.icecekAd.value.length == 0) {
			alert("Lutfen icecek ismi giriniz !");
			icecek.icecekAd.focus();
			return false;
		}

		if (icecek.icecekFiyat.value.length == 0) {
			alert("Lutfen fiyat giriniz !");
			icecek.icecekFiyat.focus();
			return false;
		}
		
		return true;
	}
</script>


</head>
<body>   

	<h2>ICECEK EKLE</h2>
	<form id="icecek" action="db_icecek_ekle.jsp"
		method="post" onSubmit="return validate(this)">
		<table width="332" height="252" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<th height="33" colspan="2"><div align="center">
						<%
						urun2 = (String) session.getAttribute("icecek");
							session.removeAttribute("icecek");
							if (urun2 != null)
								out.print(urun2);
						%>
					</div></th>
			</tr>
			<tr>
				<th>ICECEK ADI</th>
				<td><input name="icecekAdi" type="text" class="text"
					id="icecekAdi" value="" /></td>
			</tr>
			<tr>
				<th>ICECEK FIYATI</th>
				<td><input name="icecekFiyati" type="text" class="text"
					id="icecekFiyati" value="" /></td>
			</tr>

			<tr>
				<td class="submission" colspan="2"><div align="center">
						<input name="s" type="submit" class="button" value="EKLE" /> <input
							name="Reset" type="reset" class="button" value="YENILE" />
					</div></td>
			</tr>
			<tr>
				<th class="submission" colspan="2"><div align="center">
						<a href="icecek_goruntule.jsp">icecekleri Goruntule</a>
					</div></th>
			</tr>
		</table>

	</form>


</body>
</html>
