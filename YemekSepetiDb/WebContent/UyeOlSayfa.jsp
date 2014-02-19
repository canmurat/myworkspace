<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.sepetim.UyeYaz"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UYE OL</title>


</head>
<body>
	<div id='uyeolsag'>

		<form action='UyeOl.jsp' id='UyeOl' method='get'>


			<table id='uyeOlTablo'>

				<tr>
					<td>
						<h1>UYE OL</h1>
					</td>

				</tr>
				<tr>
					<td>AD</td>
					<td><input type='text' class="form-control" name='ad'><br>
					</td>
				</tr>
				<tr>
					<td>SOYAD</td>
					<td><input type='text' class="form-control" name='soyad'><br>
					</td>
				</tr>
				<tr>
					<td>DOGUM TARIH</td>
					<td><input type='text' class="form-control" name='dogumTarih'><br>
					</td>
				</tr>
				<tr>
					<td>TELEFON</td>
					<td><input type='text' class="form-control" name='telefon'><br>
					</td>
				</tr>
				<tr>
					<td>KREDI KART NO</td>
					<td><input type='text' class="form-control" name='KrediKartNo'><br>
					</td>
				<tr>
				<tr>
					<td>E-POSTA</td>
					<td><input type='text' class="form-control" name='ePosta'><br>
					</td>
				</tr>
				<tr>
					<td>SIFRE</td>
					<td><input type='password' class="form-control" name='sifre'><br>
					</td>
				</tr>
				<tr>
					<td>SIFRE TEKRAR</td>
					<td><input type='password' class="form-control"
						name='sifreTekrar'><br></td>
				</tr>
				<tr>
					<td>ADRES</td>
					<td><input type='text' class="form-control" name='adres'><br>
					</td>
				<tr>
					<td></td>
					<td><input type='submit' class='btn btn-danger' value='UYE OL'>	</td>

				</tr>
			
				
			</table>
		</form>
	</div>
	
	<div id="asagiyazilacakMesaj">
	<%
						if (session.getAttribute("sonNot") != null)
							out.println(session.getAttribute("sonNot"));
						session.removeAttribute("sonNot");
					%>
	
	
	</div>

</body>
</html>