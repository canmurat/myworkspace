<%@include file="include/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Food Online</title>
<link rel="stylesheet" href="images/style.css" type="text/css" charset="utf-8" />
</head>
<body>
<% 
yemekId1=request.getParameter("yemekId");

if(yemekId1!=null)
yemekId=Integer.parseInt(yemekId1);

rs=stmt.executeQuery("Select * from yiyecekler where Id="+yemekId);
if(rs.next())
{
yemekAd=rs.getString("Ad");
yemekFiyat=rs.getInt("Fiyat");
}

 %>
 	<!-- Yiyecekler tablosunda herhangi biryerde duzenle denildigi zaman o bilgiler yeni input textlere aktariliyor
 	ve yeniden kayıt ediliyor. -->
        <h2>YIYECEKLERI DUZENLE</h2>
        <form name="yiyecek" id="yiyecek" action="yiyecek_guncelle.jsp" method="post" >
          <table width="332" height="252" border="0" align="center" cellpadding="2" cellspacing="2">
            <tr>
              <th height="33" colspan="2"><div align="center">
			  <%
			  //yemek guncellendi ise o degeri kullaniciya soylemek icin getAttribute ile deger aliniyor.
			  guncel_yemek=(String)session.getAttribute("guncel_yemek");
			  session.removeAttribute("guncel_yemek");
			  if(guncel_yemek!=null)out.print(guncel_yemek);
			  %>
			  </div></th>
            </tr>
            <tr>
            <!--  Tabloya degerler aktarılıyor. -->
              <th>Yiyecek Adi</th>
              <td><input name="yemekAd" type="text" class="text" id="yemekAd" value="<%=yemekAd%>" /></td>
            </tr>
            <tr>
              <th>Yiyecek Fiyati </th>
              <td><input name="yemekFiyat" type="text" class="text" id="yemekFiyat" value="<%=yemekFiyat%>" /></td>
            </tr>
            
            <tr>
              <th class="submission" colspan="2"><div align="center">
              <a href="yiyecek_goruntule.jsp">Yiyecekleri Goruntule</a> </div></th>
            </tr>
            <tr>
            	<td ></td>
              <td class="submission" colspan="6">
                <input type="hidden" name="yemekId"  value="<%=yemekId%>"/>
                <input name="s" type="submit" class="button" value="GUNCELLE" />
                
              </td>
            </tr>
          </table>
        </form>
     
</body>
</html>
