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
salataId1=request.getParameter("salataId");
out.println(salataId1 + " salataId1");
if(salataId1!=null)
salataId=Integer.parseInt(salataId1);
out.println(salataId + " salataId");
rs=stmt.executeQuery("Select * from salatalar where Id="+salataId);
if(rs.next())
{
salataAd=rs.getString("Ad");
salataFiyat=rs.getInt("Fiyat");
}

 %>

        <h2>salataLERI DUZENLE</h2>
        <form name="salata" id="salata" action="salata_guncelle.jsp" method="post" >
          <table width="332" height="252" border="0" align="center" cellpadding="2" cellspacing="2">
            <tr>
              <th height="33" colspan="2"><div align="center">
			  <%
			  guncel_salata=(String)session.getAttribute("guncel_salata");
			  session.removeAttribute("guncel_salata");
			  if(guncel_salata!=null)out.print(guncel_salata);
			  %>
			  </div></th>
            </tr>
            <tr>
              <th>salata Adi</th>
              <td><input name="salataAd" type="text" class="text" id="salataAd" value="<%=salataAd%>" /></td>
            </tr>
            <tr>
              <th>salata Fiyati </th>
              <td><input name="salataFiyat" type="text" class="text" id="salataFiyat" value="<%=salataFiyat%>" /></td>
            </tr>
            
            <tr>
              <th class="submission" colspan="2"><div align="center">
              <a href="salata_goruntule.jsp">salatalari Goruntule</a> </div></th>
            </tr>
            <tr>
            	<td ></td>
              <td class="submission" colspan="6">
                <input type="hidden" name="salataId"  value="<%=salataId%>"/>
                <input name="s" type="submit" class="button" value="GUNCELLE" />
                
              </td>
            </tr>
          </table>
        </form>
     
</body>
</html>
