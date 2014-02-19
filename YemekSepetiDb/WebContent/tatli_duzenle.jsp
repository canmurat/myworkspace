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
tatliId1=request.getParameter("tatliId");
out.println(tatliId1 + " tatliId1");
if(tatliId1!=null)
tatliId=Integer.parseInt(tatliId1);
out.println(tatliId + " tatliId");
rs=stmt.executeQuery("Select * from tatlilar where Id="+tatliId);
if(rs.next())
{
tatliAd=rs.getString("Ad");
tatliFiyat=rs.getInt("Fiyat");
}

 %>

        <h2>TATLILARI DUZENLE</h2>
        <form name="tatli" id="tatli" action="tatli_guncelle.jsp" method="post" >
          <table width="332" height="252" border="0" align="center" cellpadding="2" cellspacing="2">
            <tr>
              <th height="33" colspan="2"><div align="center">
			  <%
			  guncel_tatli=(String)session.getAttribute("guncel_tatli");
			  session.removeAttribute("guncel_tatli");
			  if(guncel_tatli!=null)out.print(guncel_tatli);
			  %>
			  </div></th>
            </tr>
            <tr>
              <th>tatli Adi</th>
              <td><input name="tatliAd" type="text" class="text" id="tatliAd" value="<%=tatliAd%>" /></td>
            </tr>
            <tr>
              <th>tatli Fiyati </th>
              <td><input name="tatliFiyat" type="text" class="text" id="tatliFiyat" value="<%=tatliFiyat%>" /></td>
            </tr>
            
            <tr>
              <th class="submission" colspan="2"><div align="center">
              <a href="tatli_goruntule.jsp">tatlileri Goruntule</a> </div></th>
            </tr>
            <tr>
            	<td ></td>
              <td class="submission" colspan="6">
                <input type="hidden" name="tatliId"  value="<%=tatliId%>"/>
                <input name="s" type="submit" class="button" value="GUNCELLE" />
                
              </td>
            </tr>
          </table>
        </form>
     
</body>
</html>
