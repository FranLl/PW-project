<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/registro.css">
		<script type="text/javascript" src="/practica3/js/formLoginRegistrationVerification.js"></script>
	</head>
	<body>
<%
/* Posibles flujos:
	1) Hay parámetros en el request  -> procede del controlador /con mensaje 
	2) No hay parámetros en el request -> procede del controlador /sin mensaje
*/
String nextPage = "../controller/controladorRegistro.jsp";
String messageNextPage = request.getParameter("message");

if (messageNextPage == null) messageNextPage = "";
%>
		<p id="message"><%= messageNextPage %></p>
		<fieldset>
			<legend>Registro</legend>
			<form id="form" method="post" action="../controller/controladorRegistro.jsp">
				<div>
					<p>
						<label for="name">Nick: </label>
						<input type="text" name="nick" placeholder="Mi nick" required>
					</p>
					<p>
						<label for="name">Nombre: </label>
						<input type="text" name="name" placeholder="Mi nombre" required>
					</p>
					<p>
						<label for="surname">Apellidos: </label>
						<input type="text" name="surname" placeholder="Mis apellidos" required>
					</p>
					<p>
						<label for="email">Email: </label>
						<input type="email" name="email" id="email" placeholder="email@example.com" required>
					</p>
					<p>
						<label for="password">Password: </label>
						<input type="password" name="password" id="password" placeholder="Password" required>
					</p>
					<p>
						<label for="isAdmin">¿Administrador?: 
						<input type="checkbox" name="isAdmin" value="true">
						</label>
					</p>	
				</div>
				<input type="submit" value="Submit">
			</form>
			<form method="post" action="/practica3">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>
