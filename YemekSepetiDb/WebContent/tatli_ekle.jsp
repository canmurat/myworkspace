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
	function validate(tatli) {
		if (tatli.tatliAd.value.length == 0) {
			alert("Lutfen tatli ismi giriniz !");
			tatli.tatliAd.focus();
			return false;
		}

		if (tatli.tatliFiyat.value.length == 0) {
			alert("Lutfen fiyat giriniz !");
			tatli.tatliFiyat.focus();
			return false;
		}
		
		return true;
	}
</script>


</head>
<body>   

	<h2>tatli EKLE</h2>
	<form name="ingredient" id="tatli" action="db_tatli_ekle.jsp"
		method="post" onSubmit="return validate(this)">
		<table width="332" height="252" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<th height="33" colspan="2"><div align="center">
						<%
						urun4 = (String) session.getAttribute("tatli");
							session.removeAttribute("tatli");
							if (urun4 != null)
								out.print(urun4);
						%>
					</div></th>
			</tr>
			<tr>
				<th>TATLI ADI</th>
				<td><input name="tatliAdi" type="text" class="text"
					id="tatliAdi" value="" /></td>
			</tr>
			<tr>
				<th>TATLI FIYATI</th>
				<td><input name="tatliFiyati" type="text" class="text"
					id="tatliFiyati" value="" /></td>
			</tr>

			<tr>
				<td class="submission" colspan="2"><div align="center">
						<input name="s" type="submit" class="button" value="EKLE" /> <input
							name="Reset" type="reset" class="button" value="YENILE" />
					</div></td>
			</tr>
			<tr>
				<th class="submission" colspan="2"><div align="center">
						<a href="tatli_goruntule.jsp">tatlileri Goruntule</a>
					</div></th>
			</tr>
		</table>

	</form>


</body>
</html>
