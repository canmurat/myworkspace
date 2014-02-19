<%@include file="include/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<script type="text/javascript">
	function del() {
		if (confirm("Do You Want to Delete this Ingredient?")) {
		} else {
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="images/style.css" type="text/css"
	charset="utf-8" />
</head>
<body>
	<%
		ingredient_slno1 = request.getParameter("ingredient_slno");
		if (ingredient_slno1 != null)
			ingredient_slno = Integer.parseInt(ingredient_slno1);
		delete_ingredient = request.getParameter("delete_ingredient");

		if ("yes".equals(delete_ingredient)) {
			ingredient_code = request.getParameter("ingredient_code");
			x = stmt1
					.executeUpdate("Delete from ingredients where ingredient_slno="
							+ ingredient_slno);
		}
	%>


	<h2>VIEW INGREDIENTS</h2>

	<table width="736" height="97" border="1">
		<%
			if (x == 1) {
		%>
		<tr bgcolor="#000000">
			<th height="35" colspan="9"><div align="center">
					Ingredient (<%=ingredient_code%>) deleted successfully
				</div></th>
		</tr>
		<%
			}
		%>
		<tr bgcolor="Yellow">
			<td><div align="center">
					<strong>SLNO</strong>
				</div></td>
			<td><div align="center">
					<strong>INGREDIENT CODE </strong>
				</div></td>
			<td><div align="center">
					<strong>INGREDIENT NAME </strong>
				</div></td>
			<td><div align="center">
					<strong>INGREDIENT CATEGORY </strong>
				</div></td>
			<td><div align="center">
					<strong>INGREDIENT PRICE </strong>
				</div></td>
			<td><div align="center">
					<strong>QUANTITY</strong>
				</div></td>
			<td><div align="center">
					<strong>DATE ADDED </strong>
				</div></td>
			<td colspan="2"><div align="center">
					<strong>ACTION</strong>
				</div></td>
		</tr>
		<%
			int icount = 0;
			rs = stmt.executeQuery("select * from ingredients");
			while (rs.next()) {
				ingredient_slno = rs.getInt("ingredient_slno");
				ingredient_code = rs.getString("ingredient_code");
		%>
		<tr>
			<td><div align="center"><%=++icount%></div></td>
			<td><%=ingredient_code%></td>
			<td><%=rs.getString("ingredient_name")%></td>
			<td><%=rs.getString("ingredient_category")%></td>
			<td><%=rs.getDouble("ingredient_price")%></td>
			<td><%=rs.getDouble("quantity")%></td>
			<td><%=rs.getDate("date_added")%></td>
			<td><div align="center">
					<a href="edit_ingredient.jsp?ingredient_slno=<%=ingredient_slno%>">Edit</a>
				</div></td>
			<td><div align="center">
					<a
						href="view_ingredients.jsp?delete_ingredient=yes&ingredient_slno=<%=ingredient_slno%>&ingredient_code=<%=ingredient_code%>"
						onclick="return del()">Delete</a>
				</div></td>
		</tr>
		<%
			}
		%>
	</table>
	<a href="add_ingredient.jsp">Add Ingredient</a>

</body>
</html>

