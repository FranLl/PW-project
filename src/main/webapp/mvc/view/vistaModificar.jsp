<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<%
if (customerBean == null || customerBean.getEmail().equals("") )
{
%>
	<jsp:forward page="/">
		<jsp:param value="Acceda o reg&iacute;strese" name="message"/>
	</jsp:forward>
<%
}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modificar usuario</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/modify_user.css">
		<script type="text/javascript" src="/practica3/js/confirmationModificarUsuario.js"></script>
	</head>
	<body>
<%
String nextPage = "../controller/controladorRegistro.jsp";
String messageNextPage = request.getParameter("message");

if (messageNextPage == null) messageNextPage = "";
String password = request.getParameter("password");
%>
		<p id="message"><%= messageNextPage %></p>
		<fieldset>
			<legend>Modificar datos</legend>
			<form id="formModificarUsuario" method="post" action="../controller/controladorModificar.jsp">
				<div>
					<p>
						<label for="name">Nick: </label>
						<input type="text" name="nick" placeholder="<%= customerBean.getNick() %>">
					</p>
					<p>
						<label for="name">Nombre: </label>
						<input type="text" name="name" placeholder="<%= customerBean.getNombre() %>">
					</p>
					<p>
						<label for="surname">Apellidos: </label>
						<input type="text" name="surname" placeholder="<%= customerBean.getApellidos() %>">
					</p>
					<p>
						<label for="password">Password: </label>
						<input type="password" id="password" name="password">
					</p>
					<p>
						<label for="email">Email: </label>
						<input type="text" name="email" value="<%= customerBean.getEmail() %>" readonly>
					</p>
				</div>
				<input type="submit" value="Modificar">
			</form>
			<form id="volver" method="post" action="../controller/controladorContenido.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>
