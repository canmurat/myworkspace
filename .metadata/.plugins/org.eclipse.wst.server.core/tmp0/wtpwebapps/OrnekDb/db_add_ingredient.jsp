<%@include file="include/common.jsp"%>

<%
	ingredient_code = request.getParameter("ingredient_code");
	ingredient_name = request.getParameter("ingredient_name");
	ingredient_category = request.getParameter("ingredient_category");

	ingredient_price1 = request.getParameter("ingredient_price");
	if (ingredient_price1 != null || !"".equals(ingredient_price1))
		ingredient_price = Integer.parseInt(ingredient_price1);

	quantity1 = request.getParameter("quantity");
	if (quantity1 != null || !"".equals(quantity1))
		quantity = Integer.parseInt(quantity1);

	pstmt = con
			.prepareStatement("Insert into ingredients(ingredient_code,ingredient_name,ingredient_category,ingredient_price,quantity,date_added) values(?,?,?,?,?,?)");
	pstmt.setString(1, ingredient_code);
	pstmt.setString(2, ingredient_name);
	pstmt.setString(3, ingredient_category);
	pstmt.setDouble(4, ingredient_price);
	pstmt.setDouble(5, quantity);
	pstmt.setString(6, system_date);
	pstmt.executeUpdate();

	con.close();
	session.setAttribute("ingredient", "ingredient (" + ingredient_code
			+ ") added successfully");
	response.sendRedirect("add_ingredient.jsp");
%>