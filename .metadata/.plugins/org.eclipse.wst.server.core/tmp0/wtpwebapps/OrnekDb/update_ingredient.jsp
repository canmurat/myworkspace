<%@include file="include/common.jsp"%>

<%
	ingredient_slno1 = request.getParameter("ingredient_slno");
	if (ingredient_slno1 != null)
		ingredient_slno = Integer.parseInt(ingredient_slno1);

	ingredient_code = request.getParameter("ingredient_code");
	ingredient_name = request.getParameter("ingredient_name");
	ingredient_category = request.getParameter("ingredient_category");

	ingredient_price1 = request.getParameter("ingredient_price");
	if (ingredient_price1 != null || !"".equals(ingredient_price1))
		ingredient_price = Double.parseDouble(ingredient_price1);

	quantity1 = request.getParameter("quantity");
	if (quantity1 != null || !"".equals(quantity1))
		quantity = Double.parseDouble(quantity1);

	pstmt = con
			.prepareStatement("Update ingredients set ingredient_code=?,ingredient_name=?,ingredient_category=?,ingredient_price=?,quantity=? where ingredient_slno="
					+ ingredient_slno);

	pstmt.setString(1, ingredient_code);
	pstmt.setString(2, ingredient_name);
	pstmt.setString(3, ingredient_category);
	pstmt.setDouble(4, ingredient_price);
	pstmt.setDouble(5, quantity);
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("update_ingredient", "ingredient ("
			+ ingredient_code + ") updated successfully");
%>
<jsp:forward page="edit_ingredient.jsp">
	<jsp:param name="ingredient_slno" value="<%=ingredient_slno%>" />
</jsp:forward>
