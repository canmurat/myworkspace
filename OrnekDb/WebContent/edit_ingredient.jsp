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
ingredient_slno1=request.getParameter("ingredient_slno");
if(ingredient_slno1!=null)
ingredient_slno=Integer.parseInt(ingredient_slno1);

rs=stmt.executeQuery("Select * from ingredients where ingredient_slno="+ingredient_slno);
if(rs.next())
{
ingredient_code=rs.getString("ingredient_code");
ingredient_name=rs.getString("ingredient_name");
ingredient_category=rs.getString("ingredient_category");
ingredient_price=rs.getDouble("ingredient_price");
quantity=rs.getDouble("quantity");
}

 %>

        <h2>EDIT INGREDIENTS </h2>
        <form name="ingredient" id="ingredient" action="update_ingredient.jsp" method="post" >
          <table width="332" height="252" border="0" align="center" cellpadding="2" cellspacing="2">
            <tr>
              <th height="33" colspan="2"><div align="center">
			  <%
			  update_ingredient=(String)session.getAttribute("update_ingredient");
			  session.removeAttribute("update_ingredient");
			  if(update_ingredient!=null)out.print(update_ingredient);
			  %>
			  </div></th>
            </tr>
            <tr>
              <th>Ingredient Code </th>
              <td><input name="ingredient_code" type="text" class="text" id="ingredient_code" value="<%=ingredient_code%>" /></td>
            </tr>
            <tr>
              <th>Ingredient Name </th>
              <td><input name="ingredient_name" type="text" class="text" id="ingredient_name" value="<%=ingredient_name%>" /></td>
            </tr>
            <tr>
              <th>Ingredient Category </th>
              <td><input name="ingredient_category" type="text" class="text" id="ingredient_category" value="<%=ingredient_category%>" /></td>
            </tr>
            <tr>
              <th>Ingredient Price</th>
              <td><input name="ingredient_price" type="text" class="text" id="ingredient_price" value="<%=ingredient_price%>" /></td>
            </tr>
            <tr>
              <th>quantity</th>
              <td><input name="quantity" type="text" class="text" id="quantity" value="<%=quantity%>" /></td>
            </tr>
            
            <tr>
              <td class="submission" colspan="6">
                <input type="hidden" name="ingredient_slno"  value="<%=ingredient_slno%>"/>
                <input name="s" type="submit" class="button" value="UPDATE" />
                
              </td>
            </tr>
            <tr>
              <th class="submission" colspan="2"><div align="center"><a href="view_ingredients.jsp">View Ingredients</a> </div></th>
            </tr>
          </table>
        </form>
     
</body>
</html>
