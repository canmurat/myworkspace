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
icecekId1=request.getParameter("icecekId");
out.println(icecekId1 + " icecekId1");
if(icecekId1!=null)
icecekId=Integer.parseInt(icecekId1);
out.println(icecekId + " icecekId");
rs=stmt.executeQuery("Select * from icecekler where Id="+icecekId);
if(rs.next())
{
icecekAd=rs.getString("Ad");
icecekFiyat=rs.getInt("Fiyat");
}

 %>

        <h2>ICECEKLERI DUZENLE</h2>
        <form name="icecek" id="icecek" action="icecek_guncelle.jsp" method="post" >
          <table width="332" height="252" border="0" align="center" cellpadding="2" cellspacing="2">
            <tr>
              <th height="33" colspan="2"><div align="center">
			  <%
			  guncel_icecek=(String)session.getAttribute("guncel_icecek");
			  session.removeAttribute("guncel_icecek");
			  if(guncel_icecek!=null)out.print(guncel_icecek);
			  %>
			  </div></th>
            </tr>
            <tr>
              <th>icecek Adi</th>
              <td><input name="icecekAd" type="text" class="text" id="icecekAd" value="<%=icecekAd%>" /></td>
            </tr>
            <tr>
              <th>icecek Fiyati </th>
              <td><input name="icecekFiyat" type="text" class="text" id="icecekFiyat" value="<%=icecekFiyat%>" /></td>
            </tr>
            
            <tr>
              <th class="submission" colspan="2"><div align="center">
              <a href="icecek_goruntule.jsp">icecekleri Goruntule</a> </div></th>
            </tr>
            <tr>
            	<td ></td>
              <td class="submission" colspan="6">
                <input type="hidden" name="icecekId"  value="<%=icecekId%>"/>
                <input name="s" type="submit" class="button" value="GUNCELLE" />
                
              </td>
            </tr>
          </table>
        </form>
     
</body>
</html>
