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

 // alanların bos olması burada kontrol ediliyor. 
 // alanlar bos ise uyarı donduruluyor
	function validate(yiyecek) {
		if (yiyecek.yemekAd.value.length == 0) {
			alert("Lutfen yemek ismi giriniz !");
			yiyecek.yemekAd.focus();
			return false;
		}

		if (yiyecek.yemekFiyat.value.length == 0) {
			alert("Lutfen fiyat giriniz !");
			yiyecek.yemekFiyat.focus();
			return false;
		}
		
		return true;
	}
</script>


</head>
<body>   

<!-- Tablo burada olusturuluyor. --> 
	<h2>YIYECEK EKLE</h2>
	<form id="yiyecek" action="db_yiyecek_ekle.jsp"
		method="post" onSubmit="return validate(this)">
		<table width="332" height="252" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<th height="33" colspan="2"><div align="center">
					
					</div></th>
			</tr>
			<tr>
				<th>YIYECEK ADI</th>
				<td><input name="yiyecekAdi" type="text" class="text"
					id="yiyecekAdi" value="" /></td>
			</tr>
			<tr>
				<th>YIYECEK FIYATI</th>
				<td><input name="yiyecekFiyati" type="text" class="text"
					id="YiyecekFiyati" value="" /></td>
			</tr>

			<tr>
				<td class="submission" colspan="2"><div align="center">
						<input name="s" type="submit" class="button" value="EKLE" /> <input
							name="Reset" type="reset" class="button" value="YENILE" />
					</div></td>
			</tr>
			<tr>
				<th class="submission" colspan="2"><div align="center">
						<a href="yiyecek_goruntule.jsp">Yiyecekleri Goruntule</a>
					</div></th>
			</tr>
		</table>

	</form>


</body>
</html>
