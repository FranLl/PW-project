<%@ page language="java" contentType="text/html;  charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="include/errorPage.jsp" %>
<jsp:useBean  id="customerBean" scope="session" class="pw.p3.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Crear Sesion</title>
		<link rel="stylesheet" type="text/css" href="/practica3/css/back_n_fonts.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/box_style.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/submit_input.css">
		<link rel="stylesheet" type="text/css" href="/practica3/css/crearSesion.css">
		<script src="/practica3/js/crearSesion.js"></script>
	</head>
	<body>
		<fieldset>
			<legend>Añadir sesión</legend>
			<%
			String message = request.getParameter("message");
	
			if (message == null) message = "";
			
			if (customerBean == null || customerBean.getEmail().equals("")) {
				%>
				<jsp:forward page="../../index.jsp"/>
				<%} else {%>
				<%= message %>
				<form id="formCrearSesion" method="get" action="/practica3/crearSesion">
					<label for="fechaSesion">Fecha</label>
					<input type="datetime-local" name="fechaSesion" id="fecha" required>
					
					<input type="submit" value="Crear"/>
				</form>
				<%
			}
			%>
			<form id="volver" method="get" action="/practica3/mvc/view/adminView.jsp">
				<input type="submit" value="Volver">
			</form>
		</fieldset>
	</body>
</html>