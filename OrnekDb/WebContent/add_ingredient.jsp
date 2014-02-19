<%@ include file="include/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="images/style.css" type="text/css"
	charset="utf-8" />

<script language="javascript">
	function validate(ingredient) {
		if (ingredient.ingredient_code.value.length == 0) {
			alert("Please enter code!");
			ingredient.ingredient_code.focus();
			return false;
		}

		if (ingredient.ingredient_name.value.length == 0) {
			alert("Please enter Name!");
			ingredient.ingredient_name.focus();
			return false;
		}

		if (ingredient.ingredient_category.value.length == 0) {
			alert("Please enter category!");
			ingredient.ingredient_category.focus();
			return false;
		}

		if (ingredient.ingredient_price.value.length == 0) {
			alert("Please enter price!");
			ingredient.ingredient_price.focus();
			return false;
		}

		if (ingredient.quantity.value.length == 0) {
			alert("Please enter quantity!");
			ingredient.quantity.focus();
			return false;
		}

		return true;
	}
</script>


</head>
<body>

	<h2>ADD INGREDIENTS</h2>
	<form name="ingredient" id="ingredient" action="db_add_ingredient.jsp"
		method="post" onsubmit="return validate(this)">
		<table width="332" height="252" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<th height="33" colspan="2"><div align="center">
						<%
							ingredient = (String) session.getAttribute("ingredient");
							session.removeAttribute("ingredient");
							if (ingredient != null)
								out.print(ingredient);
						%>
					</div></th>
			</tr>
			<tr>
				<th>Ingredient Code</th>
				<td><input name="ingredient_code" type="text" class="text"
					id="ingredient_code" value="" /></td>
			</tr>
			<tr>
				<th>Ingredient Name</th>
				<td><input name="ingredient_name" type="text" class="text"
					id="ingredient_name" value="" /></td>
			</tr>
			<tr>
				<th>Ingredient Category</th>
				<td><input name="ingredient_category" type="text" class="text"
					id="ingredient_category" value="" /></td>
			</tr>
			<tr>
				<th>Ingredient Price</th>
				<td><input name="ingredient_price" type="text" class="text"
					id="ingredient_price" value="" /></td>
			</tr>
			<tr>
				<th>quantity</th>
				<td><input name="quantity" type="text" class="text"
					id="quantity" value="" /></td>
			</tr>

			<tr>
				<td class="submission" colspan="2"><div align="center">
						<input name="s" type="submit" class="button" value="ADD" /> <input
							name="Reset" type="reset" class="button" value="RESET" />
					</div></td>
			</tr>
			<tr>
				<th class="submission" colspan="2"><div align="center">
						<a href="view_ingredients.jsp">View Ingredients</a>
					</div></th>
			</tr>
		</table>

	</form>


</body>
</html>
