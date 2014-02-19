<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id='buyukdiv'>

		<div id='girissol'>
			<form action='UyeSorgulaServlet' id='ilkLogin' method='get'>
				<h1>GIRIS YAP</h1>
				<input type='text' name='username' class="form-control"><br>
				<input type='password' name='password'><br> <input
					type='submit' class='btn btn-danger' value='Giris Yap'>
			</form>
		</div>

		<div id='ortadakitanitim'></div>

		<div id='uyeolsag'>
		<form action='Uyeler' id='UyeOl' method='get'>
			<h1>UYE OL</h1>
			<input type='text' class="form-control" name='username' ><br> 
			<input type='text' class="form-control" name='usernametekrar'><br> 
			<input type='password' class="form-control" name='password'><br> 
			<input type='password'name='passwordtekrar'><br>
			<input type='text' name='mail' ><br> 
			<input type='submit' class='btn btn-danger' value='UYE OL'> 
		</form>

	</div>
	<div id='asagidakidiv'></div>
	</div>
</body>
</html>